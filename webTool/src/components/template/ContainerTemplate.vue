<template>
    <div id="container" :style="this.common.createStyle(this.layoutDatas)">
        <draggable class="full-screen" :list="layoutDatas.elements" v-model="layoutDatas.elements" group="assembly">
            <main-tempalte v-for="(item,index) in layoutDatas.elements" :key="item.id + '-' + index" :layoutData="item"/>
        </draggable>
    </div>
</template>

<script>
    import draggable from 'vuedraggable'
    import MainTempalte from "@/components/template/MainTemplate";
    export default {
        name: 'ContainerFactory',
        componentName: 'ContainerFactory',
        components: {
            draggable,
            MainTempalte
        },
        props: {
            layoutDatas: Object
        },
        methods:{

        }
    };
</script>



















