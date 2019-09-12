<template>
    <div class="full-screen">
        <draggable :class="['full-screen', 'draggable-main']" :list="elements" v-model="elements" group="assembly">
            <div :class="chooseIndex == index?'element-choose':''" v-for="(item,index) in elements" :key="item.id + '-' + index"
                 v-html="item.showHtml"
                 @click="editInfo(index,item)"/>
        </draggable>
        <gu-drawer drawerType="right" :drawerShow.sync="drawer">
            <style-edit :editData.sync="editData"></style-edit>
        </gu-drawer>
    </div>
</template>
<script>
    import draggable from 'vuedraggable'
    import GuDrawer from "@/components/gui/GuDrawer";
    import StyleEdit from "@/components/StyleEdit";

    export default {
        name: 'Layout',
        componentName: 'Layout',
        components: {
            GuDrawer,
            StyleEdit,
            draggable
        },
        data() {
            return {
                drawer: false,
                chooseIndex: -1,
                dialogFormVisible: false,
                editData: {}
            }
        },
        computed: {
            elements() {
                return this.$store.state.layout;
            }
        },
        props: {
            drawerShow: {
                type: Boolean,
                default: false
            }
        },
        watch: {
            drawerShow(val) {
                if(val == true && this.chooseIndex == -1){
                    this.$message({
                        message: '请先选择要编辑的元素!',
                        type: 'warning'
                    });
                    this.drawer = false;
                    this.$emit('update:drawerShow', false);
                }else{
                    this.drawer = val;
                }
            },
            drawer(val) {
                this.$emit('update:drawerShow', val);
            }
        },
        methods: {
            editInfo(index,data) {
                this.chooseIndex = index;
                console.log(data)
                this.editData = data;
            }
        }
    };
</script>

<style>
    .full-screen {
        height: 100%;
        width: 100%;
    }

    .draggable-main {
        background: #e9e7ee;
        flex-wrap: wrap;
        display: flex;
        align-items: flex-start;
    }

    .element-choose {
        border: 1px solid #fc4b15;
    }
</style>





















