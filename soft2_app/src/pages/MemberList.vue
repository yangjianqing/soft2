<template>
    <div>
      <h1>  会员管理</h1>
        <ul>
            <li v-for="memeber in memeberList" :key="memeber.id">
                {{memeber.name}}=>{{memeber.mobile}}
                <button v-on:click="deleteMember(memeber.id)">删除</button>
            </li>
        </ul>
    </div>
</template>
<script>

    import { listMembers,delMembers } from "@/api/members";

    export default {
        name: "MemberList",
        data() {
            return {
                memeberList: [],
                params: {
                    pageSize: 10,
                    pageNum: 0,
                    name: ""
                }
            }
        },
       methods:{
            getMembers(){
                //调用会员列表接口
                listMembers(this.params).then((res)=>{
                    this.memeberList=res.data.data;
                    console.log(res);
                }).catch((error)=>{
                    console.log(error)
                    // alert(" 友好弹框")
                })
            },
           deleteMember(id){
               delMembers(id).then((res)=>{
                   console.log(res)
                   this.getMembers();
               })
           }
       },
        mounted() {
            this.getMembers();
        }
    }
</script>

<style scoped>

</style>