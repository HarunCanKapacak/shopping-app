<template>
  <div class="col-md-12 col-lg-12">
    <div class="row">
      Comments for {{productId}}
    </div>
    <hr>
  </div>
</template>

<script>
export default {
  name: "commentsView",
  props: ['productId']
}
</script>

<style scoped>
</style>