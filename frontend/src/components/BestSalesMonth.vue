<template>
  <q-card v-if="bestMonth" class="bg-secondary text-white q-pa-md q-shadow-2">
    <div class="row no-wrap items-center justify-between">
      <!-- Icon on the left side -->
      <div class="col-auto">
        <q-icon name="paid" size="55px" color="white" />
      </div>
      <!-- Centered Text -->
      <div class="q-pl-md col-auto text-center text-h5 q-my-auto">
        Best Sales Month
      </div>
      <!-- Text on the right side -->
      <div class="col text-right q-pr-lg">
        <div class="text-h5 q-mb-xs">
          {{ bestMonth.month }}
        </div>
        <div class="text-h7 text-weight-bold q-mb-none">
          Total Sales: {{ bestMonth.totalSales.toLocaleString() }} SEK
        </div>
      </div>
    </div>
  </q-card>
</template>

<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { api } from 'src/boot/axios';

interface BestSalesMonth {
  month: string;
  totalSales: number;
}

const bestMonth = ref<{ month: string; totalSales: number } | null>(null);
const searchPerformed = ref<boolean>(false);
let intervalId: number;

const fetchBestSalesMonth = async () => {
  console.log('Fetching best sales month...'); // Log when the function starts
  searchPerformed.value = false;
  try {
    const response = await api.get<BestSalesMonth>('/sales/best-month');
    console.log('Best Sales Month:', response.data);
    bestMonth.value = response.data;
  } catch (error) {
    console.error('Error fetching best sales month:', error);
    bestMonth.value = null;
  } finally {
    searchPerformed.value = true;
  }
  console.log('response.data', bestMonth.value);
};

onMounted(() => {
  fetchBestSalesMonth(); // Fetch immediately on mount
  intervalId = window.setInterval(fetchBestSalesMonth, 10000); // Poll every 10 seconds
});

onBeforeUnmount(() => {
  if (intervalId) {
    clearInterval(intervalId); // Clear the interval when the component is destroyed
  }
});
</script>
