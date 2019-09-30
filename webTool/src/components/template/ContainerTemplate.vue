<template>
    <div id="container" style="border-radius: 5px" :style="this.common.createStyle(this.layoutDatas)">
        <draggable :list="layoutDatas.elements" class="full-screen"
                   :style="createDraggableStyle(this.layoutDatas)"
                   v-model="layoutDatas.elements" group="assembly">
            <main-tempalte v-for="(item,index) in layoutDatas.elements"
                           :key="item.id" :layout-data="item" :parent-list="layoutDatas.elements"/>
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
        methods: {
            createDraggableStyle(val) {
                let styleStr = "display: flex;flex-wrap: wrap;";

                if (!val || !val.attr || !val.attr.style){
                    styleStr += "flex-direction: row;justify-content: flex-start;align-items: flex-start;";
                }else{
                    styleStr += "flex-direction:" + this.common.isEmpty(val.attr.style.flex, 'row') + ";";
                    styleStr += "justify-content:" + this.common.isEmpty(val.attr.style.justify, 'flex-start') + ";";
                    styleStr += "align-items:" + this.common.isEmpty(val.attr.style.align, 'flex-start') + ";";
                }

                return styleStr;
            },

        }
    };
</script>



















