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
import { useCartStore } from 'src/store/cart';

// Define the Product interface
interface Product {
  id: number;
  name: string;
  size: string;
  color: string;
  price: number;
  brand: string;
  stock_quantity: number;
}

// Reference to the selected product and form fields
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

// Watch for changes in the selected product and clear dependent fields if needed
watch(selectedProduct, () => {
  if (!selectedProduct.value) {
    color.value = '';
    size.value = '';
    brand.value = '';
  }
});

// Computed options for the selects
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

// Pinia store for the shopping cart
const shoppingCart = useCartStore();

// Find the exact product based on the selected attributes
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

// Handle the form submission to add the product to the cart
const onSubmit = () => {
  if (selectedProduct.value) {
    const product = findProduct(
      selectedProduct.value.name,
      color.value,
      size.value,
      brand.value
    );

    if (product) {
      // Add the product to the shopping cart using the store's action
      shoppingCart.addItem({
        id: product.id.toString(),
        name: product.name,
        size: product.size,
        color: product.color,
        price: product.price,
        quantity: 1, // Default quantity, could be adjusted
      });

      console.log('Product added to cart:', product);
    } else {
      console.error('Product not found with the selected options');
    }
  }

  // Reset the form fields
  selectedProduct.value = null;
};
</script>

<style lang="sass">
.q-select
  padding-top: 10px
</style>
