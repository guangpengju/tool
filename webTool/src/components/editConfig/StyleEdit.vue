<template>
    <div class="edit-style edit-style-scrollbar">
        <el-form v-model="editData" label-position="left" label-width="80px">
            <el-form-item label="组件宽度">
                <el-input-number size="mini" v-model="editData.width"></el-input-number>
            </el-form-item>
            <el-form-item label="组件高度">
                <el-input-number size="mini" v-model="editData.height"></el-input-number>
            </el-form-item>

            <!-- 布局方向设置 -->
            <el-form-item label="布局方向">
                <el-radio-group v-model="editData.flex" size="small">
                    <el-radio-button label="row">水平</el-radio-button>
                    <el-radio-button label="column">垂直</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <!-- 水平布局对齐方式选项 -->
            <el-form-item label="水平对齐" v-show="editData.flex == 'row'">
                <el-radio-group v-model="editData.justify" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="center">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="space-between">两边对齐</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="垂直对齐" v-show="editData.flex == 'row'">
                <el-radio-group v-model="editData.align" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="baseline">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="stretch">占满</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <!-- 垂直布局对齐方式选项 -->
            <el-form-item label="水平对齐" v-show="editData.flex == 'column'">
                <el-radio-group v-model="editData.align" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="baseline">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="stretch">占满</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="垂直对齐" v-show="editData.flex == 'column'">
                <el-radio-group v-model="editData.justify" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="center">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="space-between">两边对齐</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="组件样式">
                <el-input type="textarea" rows="5" v-model="editData.custome"/>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: 'StyleEdit',
        componentName: 'StyleEdit',
        data(){
            return {
                editData:{
                    width: 100,
                    height: 100,
                    flex: 'row',
                    justify: 'flex-start',
                    align: 'flex-start',
                    custome: ''
                }
            }
        },
        watch: {
            '$store.state.chooseData.value': function (newVal){
                if(newVal && newVal.attr && newVal.attr.style){
                    this.editData = newVal.attr.style;
                }
            }
        },
        methods: {
            valChange(key, newVal, oldVal){
                if(this.ctl[key]){
                    if(this.ctl[key].loading == false){
                        this.editData.attr.style[key] = newVal;
                    }else {
                        this.ctl[key].loading = false;
                    }
                }
            }
        }
    };
</script>
<style>
    .edit-style{
        height: 500px;
        width: 100%;
        overflow-y: auto
    }
    .edit-style-scrollbar::-webkit-scrollbar {/*滚动条整体样式*/
        width: 4px;     /*高宽分别对应横竖滚动条的尺寸*/
        height: 4px;
        scrollbar-arrow-color:red;

    }
    .scrollbar::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
        border-radius: 5px;
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.15);
        background: rgba(0,0,0,0.2);
        scrollbar-arrow-color:red;
    }
    .scrollbar::-webkit-scrollbar-track {/*滚动条里面轨道*/
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.15);
        border-radius: 0;
        background: rgba(0,0,0,0.1);
    }
</style>





















