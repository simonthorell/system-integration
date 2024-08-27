<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Customer Purchase Totals</div>
    <q-btn
      @click="fetchCustomerTotals"
      label="Get Customer Purchase Totals"
      color="primary"
      class="q-mt-md"
    />

    <q-list v-if="customers.length" class="q-mt-md">
      <q-item-label>Customer Purchase Totals</q-item-label>
      <q-item v-for="customer in customers" :key="customer.name" clickable>
        <q-item-section
          >{{ customer.name }}: {{ customer.totalSpent }} kr</q-item-section
        >
      </q-item>
    </q-list>
    <!-- Show a banner if no customers are found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No customers found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const customers = ref<{ name: string; totalSpent: number }[]>([]);
    const searchPerformed = ref<boolean>(false);

    const fetchCustomerTotals = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/customers/purchase-totals');
        console.log('Customer Totals:', response.data);
        customers.value = response.data;
      } catch (error) {
        console.error('Error fetching customer totals:', error);
        customers.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      customers,
      searchPerformed,
      fetchCustomerTotals,
    };
  },
});
</script>
