<template>
  <q-dialog v-model="isOpen" @hide="emitClose">
    <q-card class="dialog-content">
      <q-card-section>
        <div class="text-h6">Shopping Cart</div>
      </q-card-section>

      <q-card-section class="q-pa-none">
        <!-- Cart Items List -->
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
        <q-btn flat label="Checkout" color="secondary" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { defineProps, ref, watch, defineEmits, computed } from 'vue';
import { useCartStore } from 'src/store/cart'; // Correct import path

// Props to control the dialog from parent
const props = defineProps<{
  modelValue: boolean;
}>();

// Emit value to parent when dialog state changes
const emit = defineEmits(['update:modelValue']);

// Use the shopping cart store
const shoppingCart = useCartStore();

// Map store state and actions
const cartItems = computed(() => shoppingCart.items); // Reactive cart items
const total = computed(() => shoppingCart.total); // Reactive total
const removeItem = shoppingCart.removeItem; // Direct action from the store

const isOpen = ref(props.modelValue);

// Watch for changes in props.modelValue and update local isOpen accordingly
watch(
  () => props.modelValue,
  (newVal) => {
    isOpen.value = newVal;
  }
);

function emitClose() {
  emit('update:modelValue', false);
}
</script>

<style scoped>
.dialog-content {
  width: 800px;
}

.checkout-text {
  font-size: 18px;
}
</style>
