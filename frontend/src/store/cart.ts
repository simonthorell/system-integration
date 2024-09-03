// src/store/cart.ts
import { defineStore } from 'pinia';

interface CartItem {
  id: string;
  name: string;
  size: string;
  color: string;
  price: number;
  quantity: number;
}

export const useCartStore = defineStore('cart', {
  state: () => ({
    items: [] as CartItem[],
  }),

  actions: {
    addItem(item: CartItem) {
      this.items.push(item);
    },
    removeItem(itemId: string) {
      // Remove the item by filtering it out
      this.items = this.items.filter((item) => item.id !== itemId);
    },
  },

  getters: {
    itemCount: (state) => state.items.length,
    total: (state) =>
      state.items.reduce((acc, item) => acc + item.price * item.quantity, 0),
    isCartEmpty: (state) => state.items.length === 0,
  },
});
