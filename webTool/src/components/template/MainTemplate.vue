<template>
    <div style="width: 100px;" @click.stop="choosed">
        <div :class="{'main-template':true,'main-template-choose':isChoosed}">
            <!-- 主组件 -->
            <component :is="layoutData.type == 'container'?'container-template':'assembly-template'"
                       :layoutDatas="layoutData"/>
            <!-- 移动图标 -->
            <div v-show="isChoosed" class="main-template-float" style="top: 0px;left: 0px">
                <i class="main-template-icon el-icon-rank"/>
            </div>

            <!-- 编辑图标 -->
            <div v-show="isChoosed" class="main-template-float" style="bottom: 0px;right: 0px">
                <i class="main-template-icon el-icon-edit"/>
                <i class="main-template-icon el-icon-document-copy"/>
                <i class="main-template-icon el-icon-delete"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'MainTemplate',
        componentName: 'MainTemplate',
        components: {
            ContainerTemplate: () => import("@/components/template/ContainerTemplate"),
            AssemblyTemplate: () => import("@/components/template/AssemblyTemplate")
        },
        data() {
            return {
                id: this.common.uuid(),
                isChoosed: false
            }
        },
        props: {
            layoutData: Object
        },
        watch: {
            '$store.state.chooseData.uuid': function (newVal) {
                this.isChoosed = newVal == this.id;
            }
        },
        methods: {
            choosed() {
                this.$store.dispatch('setChooseDataUUID', this.id);
                this.$store.dispatch('setChooseDataValue', this.layoutData);
                this.$emit('choosed');
            }
        }
    };
</script>

<style scoped>
    .main-template{
        border: 2px dashed hsla(0,0%,66.7%,.5);
        padding-left: 20px;
        padding-bottom: 20px;
        position: absolute;
    }

    .main-template-choose{
        outline: 3px solid #409eff;
        border: 0px;
    }

    .main-template-float{
        background: #409eff;
        color: white;
        position: absolute;
        z-index: 9999;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .main-template-icon{
        margin: 5px;
        font-size: 16px;
        display: inline-block;
        vertical-align: middle;
    }
</style>



















