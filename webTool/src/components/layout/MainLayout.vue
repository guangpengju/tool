<template>
    <div id="layout" class="full-screen">
        <!--<main-factory :layoutData="layout" @choosed="editInfo" :fullScreen="true"/>-->
        <!--<gu-drawer drawerType="right" :drawerShow.sync="drawer">
            1234567890-9
        </gu-drawer>-->
       <!-- <edit-drawer>
            <template v-slot:title>
                1234
            </template>
            <template v-slot:body>
                qwerwrwr
                &lt;!&ndash;<style-edit :editData.sync="editData"></style-edit>&ndash;&gt;
            </template>
        </edit-drawer>-->
    </div>
</template>
<script>
    import EditDrawer from "@/components/editConfig/EditDrawer";

    export default {
        name: 'MainLayout',
        componentName: 'MainLayout',
        components: {
            EditDrawer,
        },
        data() {
            return {
                editData: {}
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
    .draggable-main {
        background: #e9e7ee;
        flex-wrap: wrap;
        display: flex;
        align-items: flex-start;
    }
</style>





















