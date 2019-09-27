<template>
    <div class="edit-style scrollbar">
        <el-form label-position="left" label-width="80px">
            <el-form-item label="组件宽度" v-if="ctl.width.show">
                <el-input-number size="mini" v-model="width"></el-input-number>
            </el-form-item>
            <el-form-item label="组件高度" v-if="ctl.height.show">
                <el-input-number size="mini" v-model="height"></el-input-number>
            </el-form-item>

            <!-- 布局方向设置 -->
            <el-form-item label="布局方向" v-if="ctl.flex.show">
                <el-radio-group v-model="flex" size="small">
                    <el-radio-button label="row">水平</el-radio-button>
                    <el-radio-button label="column">垂直</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <!-- 水平布局对齐方式选项 -->
            <el-form-item label="水平对齐" v-if="ctl.justify.show" v-show="flex == 'row'">
                <el-radio-group v-model="justify" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="center">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="space-between">两边对齐</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="垂直对齐" v-if="ctl.align.show" v-show="flex == 'row'">
                <el-radio-group v-model="align" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="baseline">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="stretch">占满</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <!-- 垂直布局对齐方式选项 -->
            <el-form-item label="水平对齐" v-if="ctl.align.show" v-show="flex == 'column'">
                <el-radio-group v-model="align" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="baseline">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="stretch">占满</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="垂直对齐" v-if="ctl.justify.show" v-show="flex == 'column'">
                <el-radio-group v-model="justify" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="center">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="space-between">两边对齐</el-radio-button>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="组件样式" v-if="ctl.custome.show">
                <el-input type="textarea" rows="5" v-model="custome"/>
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
                ctl:{
                    width:{
                        loading: false,
                        show: true
                    },
                    height:{
                        loading: false,
                        show: true
                    },
                    flex:{
                        loading: false,
                        show: true
                    },
                    justify:{
                        loading: false,
                        show: true
                    },
                    align:{
                        loading: false,
                        show: true
                    },
                    custome:{
                        loading: false,
                        show: true
                    },
                },
                width: 100,
                height: 100,
                flex: 'row',
                justify: 'flex-start',
                align: 'flex-start',
                custome: ''
            }
        },
        props: {
            editData: Object
        },
        watch: {
            width(newVal, oldVal) {
                this.valChange('width', newVal, oldVal);
            },
            height(newVal, oldVal) {
                this.valChange('height', newVal, oldVal);
            },
            justify(newVal, oldVal) {
                this.valChange('justify', newVal, oldVal);
            },
            align(newVal, oldVal) {
                this.valChange('align', newVal, oldVal);
            },
            custome(newVal, oldVal) {
                let val = '';
                let customeStyleArr = newVal.split("\n");
                for (let i = 0; i < customeStyleArr.length; i++) {
                    if(customeStyleArr[i].length > 0){
                        val += (customeStyleArr[i] + (customeStyleArr[i].indexOf(";") != -1?"":";"))
                    }
                }
                this.valChange('custome', val, oldVal);
            },
            editData(){
                for (let key in this.ctl) {
                    this.ctl[key].loading = true;
                    this[key] = this.editData.attr.style[key];
                    if(key == 'custome'){
                        this[key] = this[key].replace(/;/g, ";\n");
                    }
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
    .scrollbar::-webkit-scrollbar {/*滚动条整体样式*/
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





















