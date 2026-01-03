<template>
    <div class="page-body">
        <slot />
    </div>
</template>

<script>
export default {
    name: 'PageBody'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-body {
    margin-left: 1rem;
    margin-right: 1rem;
    padding-top: 5rem;
}

.h2 {
    font-weight: 400;
}
</style>
