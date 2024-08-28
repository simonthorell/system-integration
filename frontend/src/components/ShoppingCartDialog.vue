<template>
  <q-dialog v-model="isOpen" @hide="emitClose">
    <q-card class="dialog-content">
      <q-card-section>
        <div class="text-h6">Shopping Cart</div>
      </q-card-section>

      <q-card-section>
        <!-- Cart Items List -->
        <q-list>
          <q-item v-for="(item, index) in cartItems" :key="index">
            <q-item-section>{{ item.name }}</q-item-section>
            <q-item-section>{{ item.price }} USD</q-item-section>
          </q-item>
        </q-list>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Close" color="primary" @click="isOpen = false" />
        <q-btn flat label="Checkout" color="secondary" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { defineProps, ref, watch, defineEmits } from 'vue';

// Props to control the dialog from parent
const props = defineProps<{
  modelValue: boolean;
  cartItems: { name: string; price: number }[];
}>();

// Emit value to parent when dialog state changes
const emit = defineEmits(['update:modelValue']);

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
</style>
