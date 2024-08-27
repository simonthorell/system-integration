<template>
  <q-card>
    <q-card-section>
      <div class="text-h6 text-left">Customer Purchase Totals</div>
      <q-btn
        @click="fetchCustomerTotals"
        label="Get Customer Data"
        color="primary"
        class="q-mt-md"
      />
    </q-card-section>

    <q-card-section>
      <q-table
        :rows="customers"
        :columns="columns"
        row-key="name"
        flat
        bordered
        style="min-height: 300px"
      />
    </q-card-section>

    <!-- Show a banner if no customers are found -->
    <!-- <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No customers found.
    </q-banner> -->
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';
import { QTableProps } from 'quasar';

export default defineComponent({
  setup() {
    const customers = ref<{ name: string; totalSpent: number }[]>([]);
    const searchPerformed = ref<boolean>(false);

    const columns = ref<QTableProps['columns']>([
      { name: 'name', label: 'Customer Name', field: 'name', align: 'left' },
      {
        name: 'totalSpent',
        label: 'Total Spent (kr)',
        field: 'totalSpent',
        align: 'right',
      },
    ]);

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
      columns,
      searchPerformed,
      fetchCustomerTotals,
    };
  },
});
</script>
