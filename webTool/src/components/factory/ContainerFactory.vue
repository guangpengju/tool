<template>
    <div :style="this.$parent.createStyle(element)" >
        <!--<draggable class="full-screen draggable-main" :list="element.elements" v-model="element.elements" group="assembly">
           &lt;!&ndash; <assembly-factory v-for="(item,index) in ele.elements"
                              :key="item.id + '-' + index" :element="item"
                              :assemblyClass="chooseIndex == index?'element-choose':''"/>&ndash;&gt;
        </draggable>-->
    </div>
</template>

<script>
    import draggable from 'vuedraggable'
    export default {
        name: 'ContainerFactory',
        componentName: 'ContainerFactory',
        components: {
            draggable
        },
        props: {
            element: Object
        }
    };
</script>



















