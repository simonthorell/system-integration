<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Search Ordered Products</div>
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

    <!-- Popup dialog for displaying search results -->
    <q-dialog v-model="dialogVisible">
      <q-card style="min-width: 400px">
        <q-card-section>
          <div class="text-h6">Search Results</div>
        </q-card-section>

        <q-card-section>
          <!-- Show list of customers -->
          <q-list bordered separator v-if="customers.length">
            <q-item
              v-for="customer in customers"
              :key="customer"
              clickable
              v-ripple
            >
              <q-item-section avatar>
                <q-icon color="secondary" name="person" />
              </q-item-section>
              <q-item-section>{{ customer }}</q-item-section>
            </q-item>
          </q-list>

          <!-- Show a message if no customers are found -->
          <q-banner v-else>
            No customers found for the provided criteria.
          </q-banner>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn
            flat
            label="Close"
            color="primary"
            @click="dialogVisible = false"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-card>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { api } from 'src/boot/axios'; // Import the api instance from the boot file

const productName = ref<string>('');
const color = ref<string>('');
const size = ref<string>('');
const brand = ref<string>('');
const customers = ref<string[]>([]);
const searchPerformed = ref<boolean>(false);
const dialogVisible = ref<boolean>(false); // Control dialog visibility

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
    dialogVisible.value = true; // Show the dialog with results
  }
};
</script>

<style lang="sass">
.q-input
  padding-top: 10px

.q-dialog
  width: 800px
</style>
