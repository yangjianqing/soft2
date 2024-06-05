<template>
    <div class="nav">

      <div class="no_shopping">
        <h4>您还没有购买任何商品</h4>
      </div>
    </div>
</template>

<script>
  export default {
    data(){
      return{

      }
    }
  }
</script>



<style scoped>
.nav{
  height: 277px;
  background-color: rgb(239,250,252);
  padding-top: 1px;
}
.no_shopping{
  margin: 3%;
  height: 216px;
  background-color: white;
  border-radius: 4%;
  box-shadow: 1px 16px 20px -7px rgba(216,225,213);
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
