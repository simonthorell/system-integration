<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Orders by City</div>

    <!-- Input and Search Button -->
    <div class="q-pt-sm row items-center q-gutter-md">
      <q-input
        v-model="threshold"
        label="Enter Threshold"
        type="number"
        outlined
        dense
        class="col"
      />
      <q-btn
        @click="fetchOrders"
        label="Search"
        color="primary"
        class="col-auto q-ml-sm q-mt-lg"
      />
    </div>

    <!-- Table to Display Results -->
    <div class="q-pt-md">
      <q-table
        :rows="orders"
        :columns="columns"
        row-key="city"
        flat
        class="fixed-table"
      >
      </q-table>
    </div>
  </q-card>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { api } from 'src/boot/axios';

interface Order {
  city: string;
  total_order_value: number;
}

const threshold = ref<number>(100);
const orders = ref<Order[]>([]);
const searchPerformed = ref<boolean>(false);

const columns: Array<{
  name: string;
  label: string;
  field: keyof Order | ((row: Order) => string | number);
  required?: boolean;
  align?: 'left' | 'right' | 'center';
}> = [
  {
    name: 'city',
    required: true,
    label: 'City',
    align: 'left',
    field: 'city',
  },
  {
    name: 'total_order_value',
    required: true,
    label: 'Total Order Value',
    align: 'right',
    field: 'total_order_value',
  },
];

const fetchOrders = async () => {
  searchPerformed.value = false;
  try {
    const response = await api.get('/sales/get-order-for-city', {
      params: { threshold: threshold.value },
    });
    orders.value = response.data;
  } catch (error) {
    console.error('Error fetching orders:', error);
    orders.value = [];
  } finally {
    searchPerformed.value = true;
  }
};

// Automatically fetch data when the component is mounted
onMounted(() => {
  fetchOrders();
});
</script>

<style lang="sass" scoped>
.fixed-table
  min-height: 328px
  max-height: 328px
  overflow-y: auto
</style>
