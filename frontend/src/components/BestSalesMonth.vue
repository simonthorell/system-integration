<template>
  <q-card class="q-pa-md">
    <q-card-section>
      <div class="text-h6 text-left">Best Sales Month</div>
    </q-card-section>

    <q-card-section v-if="bestMonth" class="q-mt-md">
      <q-card class="bg-secondary text-white q-pa-md q-mx-md q-shadow-2">
        <div class="row no-wrap items-center justify-between">
          <!-- Icon on the left side -->
          <div class="col-auto">
            <q-icon name="trending_up" size="64px" color="white" />
          </div>
          <!-- Text on the right side -->
          <div class="col text-right q-pr-lg">
            <div class="text-h4 q-mb-xs">
              {{ bestMonth.month }}
            </div>
            <div class="text-h5 text-weight-bold q-mb-none">
              {{ bestMonth.totalSales.toLocaleString() }} kr
            </div>
          </div>
        </div>
      </q-card>
    </q-card-section>

    <!-- Show a banner if no data is found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No sales data found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

interface BestSalesMonth {
  month: string;
  totalSales: number;
}

export default defineComponent({
  setup() {
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

    return {
      bestMonth,
      searchPerformed,
      fetchBestSalesMonth,
    };
  },
});
</script>
