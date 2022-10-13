<template>
  <div class="row">
    {{ productFeatures }}
  </div>
  <hr/>
  <div class="row">
    info 2
  </div>
  <hr/>
  <div class="row">
    info 3
  </div>
  <hr/>
  <div class="row">
    info 4
  </div>
</template>

<script>
export default {
  name: "featuresView",
  props: ['productFeatures']
}
</script>

<style scoped>
</style>