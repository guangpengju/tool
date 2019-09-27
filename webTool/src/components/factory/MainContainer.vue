<template>
    <div id="container" :style="this.$parent.createStyle(this.layoutDatas)">
        <draggable class="full-screen" :list="layoutDatas.elements" v-model="layoutDatas.elements" group="assembly">
            <main-factory v-for="(item,index) in layoutDatas.elements" :key="item.id + '-' + index" :layoutData="item"/>
        </draggable>
    </div>
</template>

<script>
    import draggable from 'vuedraggable'
    import MainFactory from "@/components/factory/MainFactory";
    export default {
        name: 'ContainerFactory',
        componentName: 'ContainerFactory',
        components: {
            MainFactory,
            draggable
        },
        props: {
            layoutDatas: Object
        },
        methods:{
            /*choose(){
                this.$store.dispatch('setChooseDataValue', this.layoutDatas);
                this.$emit('choosed');
            }*/
        }
    };
</script>



















