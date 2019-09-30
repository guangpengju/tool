<template>
    <!-- 侧弹框 -->
    <div v-show="drawerShow" class="gu-drawer-base gu-drawer-right">
        <el-tabs stretch style="margin: 5px 0px">
            <!-- 标签页 -->
            <el-tab-pane v-for="item in tabs">
                <!-- 标签头 -->
                <span slot="label" class="tab-label">
                    <mu-icon size="14" :value="item.icon" class="tab-label-icon"/>{{item.label}}
                </span>

                <!-- 标签体 -->
                <div style="padding: 0px 0px 0px 10px">
                    <component :is="item.component"/>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
alal
<script>
    import StyleEdit from "@/components/editConfig/StyleEdit";
    export default {
        name: 'EditDrawer',
        components: {StyleEdit},
        componentName: 'EditDrawer',
        data() {
            return {
                tabs:[{
                    label: '属性',
                    icon: ':el-icon-s-operation',
                    component: 'StyleEdit'
                },{
                    label: '事件',
                    icon: ':el-icon-magic-stick',
                    component: 'StyleEdit'
                },{
                    label: '样式',
                    icon: ':el-icon-help',
                    component: 'StyleEdit'
                }]
            }
        },
        props:{
            drawerShow: Boolean
        }
    };
</script>

<style scoped>
    .gu-drawer-base {
        height: 100%;
        width: 380px;
        background: #ffffff;
        overflow: hidden;
        z-index: 999;
        position: fixed;
        border: 2px solid #F7F7F7;
        border-right: 1px solid #AAAAAA;
        padding: 0px 3px;
    }

    .gu-drawer-right {
        margin-top: 0px;
        right: 24px;
    }

    .tab-label{
        font-size: 14px;
        font-weight: bold;
    }

    .tab-label-icon{
        margin: 0px 5px 0px 3px;
    }
</style>




















