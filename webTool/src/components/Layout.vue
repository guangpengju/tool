<template>
    <div id="layout" class="full-screen">
        <main-factory :layoutData="layout" @choosed="editInfo"/>
        <gu-drawer drawerType="right" :drawerShow.sync="drawer">
            <style-edit :editData.sync="editData"></style-edit>
        </gu-drawer>
    </div>
</template>
<script>
    import GuDrawer from "@/components/gui/GuDrawer";
    import StyleEdit from "@/components/StyleEdit";
    import MainFactory from "@/components/factory/MainFactory";

    export default {
        name: 'Layout',
        componentName: 'Layout',
        components: {
            MainFactory,
            GuDrawer,
            StyleEdit
        },
        data() {
            return {
                drawer: false,
                dialogFormVisible: false,
                editData: {}
            }
        },
        computed: {
            layout() {
                console.log(typeof this.$store.state.layout)
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
                if (val == true && this.chooseIndex == -1) {
                    this.$message({
                        message: '请先选择要编辑的元素!',
                        type: 'warning'
                    });
                    this.drawer = false;
                    this.$emit('update:drawerShow', false);
                } else {
                    this.drawer = val;
                }
            },
            drawer(val) {
                this.$emit('update:drawerShow', val);
            }
        },
        methods: {
            editInfo() {
                // this.editData = this.$store.state.chooseData.value;
            }
        }
    };
</script>

<style>
    .draggable-main {
        background: #e9e7ee;
        flex-wrap: wrap;
        display: flex;
        align-items: flex-start;
    }
</style>





















