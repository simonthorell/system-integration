<template>
  <q-page class="q-pa-md items-center justify-evenly page-background">
    <div class="row q-col-gutter-md q-mb-md">
      <div class="col-12 col-md-4">
        <BestSalesMonth />
      </div>
      <div class="col-12 col-md-4">
        <CustomerPurchaseTotal />
      </div>
      <div class="col-12 col-md-4">
        <OrderValuePerCity />
      </div>
    </div>
    <div class="row q-col-gutter-md q-mb-md">
      <div class="col-12 col-md-4">
        <ProductsPerCategory />
      </div>
      <div class="col-12 col-md-4">
        <SearchOrder />
      </div>
      <div class="col-12 col-md-4">
        <Top5Products />
      </div>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import BestSalesMonth from 'src/components/BestSalesMonth.vue';
import CustomerPurchaseTotal from 'src/components/CustomerPurchaseTotal.vue';
import OrderValuePerCity from 'src/components/OrderValuePerCity.vue';
import ProductsPerCategory from 'src/components/ProductsPerCategory.vue';
import SearchOrder from 'src/components/SearchOrder.vue';
import Top5Products from 'src/components/Top5Products.vue';

defineOptions({
  name: 'IndexPage',
  components: {
    BestSalesMonth,
    CustomerPurchaseTotal,
    OrderValuePerCity,
    ProductsPerCategory,
    SearchOrder,
    Top5Products,
  },
});
</script>

<style scoped>
.page-background {
  background-image: url('../assets/bg_1.jpg');
  background-size: cover;
  background-position: center;
}
</style>
