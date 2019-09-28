<template>
    <div id="layout" class="full-screen">
        <draggable class="full-screen main-container" :list="editData" v-model="editData" group="assembly">
            <main-template v-for="(item,index) in editData" :key="item.id + '-' + index" :layoutData="item"/>
        </draggable>
    </div>
</template>
<script>
    import draggable from 'vuedraggable'
    import MainTemplate from "@/components/template/MainTemplate";
    export default {
        name: 'MainLayout',
        componentName: 'MainLayout',
        components: {
            draggable,
            MainTemplate
        },
        data() {
            return {
                editData: this.$store.state.layout
            }
        },
        methods: {
            /*editInfo() {
                // this.editData = this.$store.state.chooseData.value;
            }*/
        }
    };
</script>

<style scoped>

    .main-container{
        background: rgba(92, 234, 24, 0.1);
        display: flex;
        flex-wrap: wrap;
    }
</style>





















