<template>
  <q-card class="q-pa-md">
    <div class="text-h6 text-left q-mb-md">Customer Purchase Totals</div>

    <div>
      <div class="scrollable-container">
        <q-intersection
          class="intersection"
          v-for="(customer, index) in customers"
          :key="index"
          transition="scale"
        >
          <q-item clickable v-ripple class="customer-item">
            <q-item-section avatar>
              <q-avatar color="secondary" text-color="white">
                {{ index + 1 }}
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label class="customer-name-label">
                {{ customer.first_name }} {{ customer.last_name }}
              </q-item-label>
              <q-item-label caption lines="1">
                Total Spent: {{ customer.total_spent }} SEK
              </q-item-label>
            </q-item-section>

            <q-item-section side>
              <q-icon name="attach_money" color="yellow" />
            </q-item-section>
          </q-item>
        </q-intersection>
      </div>
    </div>

    <!-- Show a banner if no customers are found -->
    <q-banner v-if="searchPerformed && customers.length === 0" class="q-mt-md">
      No customers found.
    </q-banner>
  </q-card>
</template>

<script lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { defineComponent } from 'vue';
import { api } from 'src/boot/axios';

export default defineComponent({
  setup() {
    const customers = ref<
      {
        first_name: string;
        last_name: string;
        total_spent: number;
      }[]
    >([]);
    const searchPerformed = ref<boolean>(false);
    let intervalId: number;

    const fetchCustomers = async () => {
      searchPerformed.value = false;
      try {
        const response = await api.get('/customers/purchase-total');
        customers.value = response.data;
      } catch (error) {
        console.error('Error fetching customer spending data:', error);
        customers.value = [];
      } finally {
        searchPerformed.value = true;
      }
    };

    onMounted(() => {
      fetchCustomers();
      // Poll backend for updates
      intervalId = window.setInterval(fetchCustomers, 10000);
    });

    onBeforeUnmount(() => {
      if (intervalId) {
        clearInterval(intervalId); // Clear the interval when the component is destroyed
      }
    });

    return {
      customers,
      searchPerformed,
      fetchCustomers,
    };
  },
});
</script>

<style lang="sass" scoped>
.scrollable-container
  height: 385px
  overflow-y: auto

.intersection
  height: 65px

.customer-item
  border-radius: 8px
  margin-bottom: 8px
  background-color: $primary
  transition: background-color 0.3s ease, transform 0.3s ease

  &:hover
    background-color: $primary
    transform: scale(1.02)

.customer-name-label
  font-size: 1.2rem
  font-weight: bold
  color: white
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2)
  transition: color 0.3s ease

  &:hover
    color: $secondary
</style>
