<template>
  <q-page class="q-pa-md">
    <q-form @submit="onSubmit">
      <q-input v-model="productName" label="Product Name" outlined />
      <q-input v-model="color" label="Color" outlined />
      <q-input v-model="size" label="Size" outlined />
      <q-input v-model="brand" label="Brand" outlined />
      <q-btn
        type="submit"
        label="Find Customers"
        color="primary"
        class="q-mt-md"
      />
    </q-form>

    <q-list v-if="customers.length" class="q-mt-md">
      <q-item-label>Customers Who Bought {{ productName }}</q-item-label>
      <q-item v-for="customer in customers" :key="customer" clickable>
        <q-item-section>{{ customer }}</q-item-section>
      </q-item>
    </q-list>
    <!-- Show a banner if no customers are found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No customers found for the provided criteria.
    </q-banner>
  </q-page>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios'; // Import the api instance from the boot file

export default defineComponent({
  setup() {
    const productName = ref<string>('');
    const color = ref<string>('');
    const size = ref<string>('');
    const brand = ref<string>('');
    const customers = ref<string[]>([]);
    const searchPerformed = ref<boolean>(false);

    const onSubmit = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get<string[]>('/customers/who-bought', {
          params: {
            productName: productName.value,
            color: color.value,
            size: size.value,
            brand: brand.value,
          },
        });
        console.log('Customers:', response.data);
        customers.value = response.data;
      } catch (error) {
        console.error('Error fetching customers:', error);
        customers.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      productName,
      color,
      size,
      brand,
      customers,
      searchPerformed,
      onSubmit,
    };
  },
});
</script>

<style lang="sass">
.q-input
  padding-top: 10px
</style>
