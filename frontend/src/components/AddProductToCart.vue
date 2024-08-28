<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Add Product to Shopping Cart</div>
    <q-form @submit="onSubmit">
      <!-- Product Selection from Database -->
      <q-select
        v-model="selectedProduct"
        :options="productOptions"
        label="Product Name"
        outlined
        use-input
        clearable
        option-value="id"
        option-label="name"
      />
      <q-select
        v-model="color"
        :options="colorOptions"
        label="Color"
        outlined
        use-input
        clearable
        option-value="id"
        option-label="color"
        :disable="selectedProduct == null"
      />
      <q-select
        v-model="size"
        :options="sizeOptions"
        label="Size"
        outlined
        use-input
        clearable
        option-value="id"
        option-label="size"
        :disable="selectedProduct == null"
      />
      <q-select
        v-model="brand"
        :options="brandOptions"
        label="Brand"
        outlined
        use-input
        clearable
        option-value="id"
        option-label="brand"
        :disable="selectedProduct == null"
      />

      <!-- Submit Button -->
      <q-btn
        type="submit"
        label="Add to Cart"
        color="primary"
        class="q-mt-md"
      />
    </q-form>
  </q-card>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, watch } from 'vue';
import { api } from 'src/boot/axios';

interface Product {
  id: number;
  name: string;
  size: string;
  color: string;
  price: number;
  brand: string;
  stock_quantity: number;
}

const selectedProduct = ref<Product | null>(null);
const products = ref<Product[]>([]);
const color = ref<string>('');
const size = ref<string>('');
const brand = ref<string>('');

// Fetch the list of products from the backend
const fetchProducts = async () => {
  try {
    const response = await api.get<Product[]>('/product');
    products.value = response.data;
  } catch (error) {
    console.error('Error fetching products:', error);
  }
};
// Get all products when the component is mounted
onMounted(() => {
  fetchProducts();
});

// Watch for changes in the selected product and clear if needed
watch(selectedProduct, () => {
  if (!selectedProduct.value || selectedProduct.value === null) {
    color.value = '';
    size.value = '';
    brand.value = '';
  }
});

const productOptions = computed(() => {
  const uniqueProductNames = new Set(
    products.value.map((product) => product.name)
  );

  return Array.from(uniqueProductNames).map((name) => ({
    id: name,
    name: name,
  }));
});

const colorOptions = computed(() => {
  if (!selectedProduct.value) {
    return [];
  }
  const uniqueColors = new Set(
    products.value
      .filter((product) => product.name === selectedProduct.value?.name)
      .map((product) => product.color)
  );
  return Array.from(uniqueColors);
});

const sizeOptions = computed(() => {
  if (!selectedProduct.value) {
    return [];
  }
  const uniqueSizes = new Set(
    products.value
      .filter((product) => product.name === selectedProduct.value?.name)
      .map((product) => product.size)
  );
  return Array.from(uniqueSizes);
});

const brandOptions = computed(() => {
  if (!selectedProduct.value) {
    return [];
  }
  const uniqueBrands = new Set(
    products.value
      .filter((product) => product.name === selectedProduct.value?.name)
      .map((product) => product.brand)
  );
  return Array.from(uniqueBrands);
});

// Find the product based on product name, color, size, and brand
const findProduct = (
  name: string,
  color: string,
  size: string,
  brand: string
) => {
  return products.value.find(
    (product) =>
      product.name === name &&
      product.color === color &&
      product.size === size &&
      product.brand === brand
  );
};

const onSubmit = async () => {
  if (selectedProduct.value) {
    const productId = findProduct(
      selectedProduct.value.name,
      color.value,
      size.value,
      brand.value
    );
    console.log('Product ID:', productId);
    console.log('Product added to cart:', selectedProduct.value);
  }
};
</script>

<style lang="sass">
.q-select
  padding-top: 10px
</style>
