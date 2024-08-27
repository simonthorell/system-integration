<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Best Sales Month</div>
    <q-btn
      @click="fetchBestSalesMonth"
      label="Get Best Sales Month"
      color="primary"
      class="q-mt-md"
    />

    <q-list v-if="bestMonth" class="q-mt-md">
      <q-item-label>Best Sales Month</q-item-label>
      <q-item>
        <q-item-section
          >{{ bestMonth.name }}: {{ bestMonth.totalSales }} kr</q-item-section
        >
      </q-item>
    </q-list>
    <!-- Show a banner if no data is found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No sales data found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const bestMonth = ref<{ name: string; totalSales: number } | null>(null);
    const searchPerformed = ref<boolean>(false);

    const fetchBestSalesMonth = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/sales/best-month');
        console.log('Best Sales Month:', response.data);
        bestMonth.value = response.data;
      } catch (error) {
        console.error('Error fetching best sales month:', error);
        bestMonth.value = null;
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      bestMonth,
      searchPerformed,
      fetchBestSalesMonth,
    };
  },
});
</script>
