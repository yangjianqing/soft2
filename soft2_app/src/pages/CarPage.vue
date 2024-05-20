<template>
    <div>
        <CarList :goodsList="$store.state.carList"></CarList>
    </div>
</template>

<script>
    import CarList from "@/components/CarList";
    export default {
        name: "CarPage",
        components: {CarList}
    }
</script>

<style scoped>

</style>