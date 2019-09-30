<template>
    <div :class="{'main-template':true,'main-template-choose':isChoosed}" @click.stop="choosed">
        <!-- 主组件 -->
        <div style="border: 1px dotted hsla(0,0%,66.7%,.5);border-radius: 5px">
            <component :is="layoutData.type == 'container'?'container-template':'assembly-template'"
                       :layoutDatas="layoutData"/>
        </div>
        <!-- 移动图标 -->
        <div v-show="isChoosed" class="main-template-float" style="top: 0px;left: 0px">
            <i class="main-template-icon el-icon-rank"/>
        </div>

        <!-- 编辑图标 -->
        <div v-show="isChoosed" class="main-template-float" style="bottom: 0px;right: 0px">
            <i class="main-template-icon el-icon-edit"/>
            <i class="main-template-icon el-icon-document-copy" @click="copyElement"/>
            <i class="main-template-icon el-icon-delete" @click="deleteElement"/>
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
                isChoosed: false
            }
        },
        props: {
            layoutData: Object,
            parentList: Array
        },
        watch: {
            '$store.state.chooseData.uuid': function (newVal) {
                this.isChoosed = newVal == this.layoutData.uuid;
            }
        },
        methods: {
            choosed() {
                this.$store.dispatch('setChooseDataUUID', this.layoutData.uuid);
                this.$store.dispatch('setChooseDataValue', this.layoutData);
                this.$emit('choosed');
            },
            deleteElement(){
                for (let i = 0; i < this.parentList.length; i++) {
                    if(this.parentList[i].id == this.layoutData.id){
                        this.parentList.splice(i,1);
                        break;
                    }
                }
            },
            copyElement(){
                let obj = this.common.createElement(this.layoutData)
                this.parentList.push(obj);
            }
        }
    };
</script>

<style scoped>
    .main-template{
        border: 2px dashed hsla(0,0%,66%,.5);
        background: rgba(236,245,255,.3);
        padding: 3px 3px 20px 20px;
        position: relative;
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

    .main-template-icon:active {
        color: #bbbbbb;
    }

</style>



















