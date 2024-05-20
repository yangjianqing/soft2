<template>
    <swiper
            modules
            navigation
            :pagination="{ clickable: true }"
            :scrollbar="{ draggable: true }"
            @swiper="onSwiper"
            @slideChange="onSlideChange"
    >
        <swiper-slide v-for="(item, index) in imgList" :key="index">
            <el-image :src="item.url" fit="contain"></el-image>
        </swiper-slide>
    </swiper>
</template>
<script>
    // 这里需要什么其他功能可以查询文档
    import { Navigation, Pagination, A11y } from 'swiper/modules'
    import { Swiper, SwiperSlide } from 'swiper/vue'

    // 导入插件样式
    import 'swiper/css'
    import 'swiper/css/navigation' // 左右按钮
    import 'swiper/css/pagination' // 分页小圆点

    export default {
        components: {
            Swiper,
            SwiperSlide,
        },
        setup() {
            const onSwiper = (swiper) => {
                console.log(swiper);
            };
            const onSlideChange = () => {
                console.log('slide change');
            };
            return {
                onSwiper,
                onSlideChange,
                modules: [Navigation, Pagination, A11y],
            };
        },
    };
</script>