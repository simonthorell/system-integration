<template>
  <q-dialog v-model="isOpen" @hide="emitClose">
    <q-card class="dialog-content">
      <q-card-section class="q-pb-none">
        <div class="text-h6">Shopping Cart</div>
      </q-card-section>

      <!-- Customer Selection and New Customer Input -->
      <q-card-section>
        <div class="row q-col-gutter-md items-center">
          <div class="col-10">
            <!-- Select existing customer -->
            <q-select
              v-model="selectedCustomer"
              :options="customerOptions"
              label="Select Customer"
              outlined
              clearable
              option-value="id"
              option-label="name"
              @update:model-value="onCustomerChange"
            />
          </div>
          <div class="col-2 flex justify-center">
            <!-- Button for new customer -->
            <q-btn disable round icon="add" color="accent"></q-btn>
          </div>
        </div>
      </q-card-section>

      <!-- Cart Items List -->
      <q-card-section class="q-pa-none q-pt-sm">
        <q-list bordered padding class="rounded-borders">
          <q-item
            clickable
            v-ripple
            v-for="(item, index) in cartItems"
            :key="index"
          >
            <q-item-section avatar top>
              <q-avatar
                icon="shopping_cart"
                color="secondary"
                text-color="white"
              />
            </q-item-section>

            <q-item-section>
              <q-item-label lines="1"
                >{{ item.name }} ({{ item.size }},
                {{ item.color }})</q-item-label
              >
            </q-item-section>

            <q-item-section>
              <q-item-label caption
                >Quantity: {{ item.quantity }} pcs</q-item-label
              >
              <q-item-label caption
                >Item Price: {{ item.price }} SEK</q-item-label
              >
            </q-item-section>

            <q-item-section side>
              <q-icon name="delete" color="grey" @click="removeItem(item.id)" />
            </q-item-section>
          </q-item>
        </q-list>
      </q-card-section>

      <q-card-section class="q-pa-none">
        <q-item>
          <q-item-section>
            <q-item-label class="checkout-text">Total Price:</q-item-label>
          </q-item-section>
          <q-item-section side>
            <q-item-label class="checkout-text">{{ total }} SEK</q-item-label>
          </q-item-section>
        </q-item>
      </q-card-section>

      <q-separator />

      <q-card-actions align="right">
        <q-btn flat label="Close" color="primary" @click="isOpen = false" />
        <q-btn flat label="Checkout" color="secondary" @click="checkout" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { ref, watch, computed } from 'vue';
import { api } from 'src/boot/axios';
import { useCartStore } from 'src/store/cart';

interface Customer {
  id: number;
  first_name: string;
  last_name: string;
}

// Props to control the dialog from parent
const props = defineProps<{
  modelValue: boolean;
}>();

// Emit value to parent when dialog state changes
const emit = defineEmits(['update:modelValue']);

// Use the shopping cart store
const shoppingCart = useCartStore();

// Reactive state for cart items and total
const cartItems = computed(() => shoppingCart.items);
const total = computed(() => shoppingCart.total);
const removeItem = shoppingCart.removeItem;

// Reactive state for customer selection and input
const selectedCustomer = ref<number | null>(null);
const newCustomer = ref<string>('');

// Customer options
const customerOptions = ref<{ id: number; name: string }[]>([]);

// Watch for changes in isOpen and fetch customers when the dialog opens
const isOpen = ref(props.modelValue);
watch(
  () => isOpen.value,
  async (newVal) => {
    if (newVal) {
      await fetchCustomers(); // Fetch customers when the dialog is opened
    }
  }
);

// Watch for changes in props.modelValue and update local isOpen accordingly
watch(
  () => props.modelValue,
  (newVal) => {
    isOpen.value = newVal;
  }
);

// Function to fetch customers from the backend
const fetchCustomers = async () => {
  try {
    const response = await api.get('/customers');
    // console.log('Fetched customers:', response.data);
    customerOptions.value = response.data.map((customer: Customer) => ({
      id: customer.id,
      name: `${customer.first_name} ${customer.last_name}`,
    }));
  } catch (error) {
    console.error('Failed to fetch customers:', error);
  }
};

// Function to handle customer change
const onCustomerChange = () => {
  newCustomer.value = ''; // Clear new customer field if an existing customer is selected
};

// Function to emit close event
function emitClose() {
  emit('update:modelValue', false);
}

// Function to handle checkout (example)
const checkout = async () => {
  if (!selectedCustomer.value) {
    console.error('Please select a customer before checking out.');
    return;
  }

  // Prepare the order data
  const orderData = {
    customer_id: selectedCustomer.value.id, // Selected customer ID
    products: cartItems.value.map((item) => ({
      product_id: item.id,
      quantity: item.quantity,
    })),
  };

  // console.log('Order data:', orderData);

  try {
    // Send the order data to the backend
    const response = await api.post('/order', orderData);
    console.log('Order placed successfully:', response.data);

    // Handle successful order placement (e.g., clear cart, show success message)
    shoppingCart.clearCart(); // Example: Clear the cart
    isOpen.value = false; // Close the dialog
  } catch (error) {
    console.error('Failed to place the order:', error);
  }
};
</script>

<style scoped>
.dialog-content {
  width: 800px;
}

.checkout-text {
  font-size: 18px;
}
</style>
