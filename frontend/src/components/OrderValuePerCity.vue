<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left">Search For City By Order Value</div>
    <q-input v-model="threshold" label="Minimum Order Value" outlined />
    <q-btn
      @click="fetchCityOrderValues"
      label="Get Order Values per City"
      color="primary"
      class="q-mt-md"
    />

    <q-list v-if="cities.length" class="q-mt-md">
      <q-item-label>Order Values per City</q-item-label>
      <q-item v-for="city in cities" :key="city.name" clickable>
        <q-item-section
          >{{ city.name }}: {{ city.totalOrderValue }} kr</q-item-section
        >
      </q-item>
    </q-list>
    <!-- Show a banner if no cities are found -->
    <q-banner v-else-if="searchPerformed" class="q-mt-md">
      No cities found with order values above {{ threshold }} kr.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const threshold = ref<number>(1000);
    const cities = ref<{ name: string; totalOrderValue: number }[]>([]);
    const searchPerformed = ref<boolean>(false);

    const fetchCityOrderValues = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/orders/city-totals', {
          params: {
            threshold: threshold.value,
          },
        });
        console.log('City Order Values:', response.data);
        cities.value = response.data;
      } catch (error) {
        console.error('Error fetching city order values:', error);
        cities.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    return {
      threshold,
      cities,
      searchPerformed,
      fetchCityOrderValues,
    };
  },
});
</script>
