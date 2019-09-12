<template>
    <div>
        <el-form label-position="left" label-width="80px" :model="eleData">
            <el-form-item label="组件宽度">
                <el-input-number size="mini" v-model="eleData.style.width"></el-input-number>
            </el-form-item>
            <el-form-item label="组件高度">
                <el-input-number size="mini" v-model="eleData.style.height"></el-input-number>
            </el-form-item>
            <el-form-item label="水平对齐">
                <el-radio-group v-model="eleData.style.justify" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="center">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="space-between">两边对齐</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="垂直对齐">
                <el-radio-group v-model="eleData.style.align" size="small">
                    <el-radio-button label="flex-start">左对齐</el-radio-button>
                    <el-radio-button label="baseline">居中</el-radio-button>
                    <el-radio-button label="flex-end">右对齐</el-radio-button>
                    <el-radio-button label="stretch">占满</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="组件样式">
                <el-input type="textarea" rows="5" v-model="eleData.style.custome"/>
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
                isLoadHtml: false
            }
        },
        computed: {
            eleData() {
                if(this.editData.style == undefined){
                    return {
                        id: 'default-data',
                        style:{
                            width: 100,
                            height: 100,
                            direction: "row",
                            justify: "flex-start",
                            align: "stretch",
                            custome: ""
                        }
                    }
                }else{
                    return this.editData
                }
            }
        },
        props: {
            editData: Object
        },
        watch: {
            editData:{
                handler(val) {
                    if(!this.isLoadHtml){
                        this.isLoadHtml = true;
                        this.loadHtml(val);
                        this.$emit('update:editData', val);
                        this.isLoadHtml = false;
                    }
                },
                deep: true
            },
            eleData: {
                handler(val) {
                    if(!this.isLoadHtml){
                        this.isLoadHtml = true;
                        this.loadHtml(val);
                        this.$emit('update:editData', val);
                        this.isLoadHtml = false;
                    }
                },
                deep: true
            }
        },
        methods: {
            loadHtml(ele) {
                if(ele){
                    let html = new DOMParser().parseFromString(ele.showHtml, "text/xml");
                    let div = html.getElementsByTagName(ele.tage)[0];
                    let styleVal = div.attributes['style'].value;

                    console.log(styleVal);
                    let style = "";
                    let styleArr = styleVal.split(";");
                    for (let i = 0; i < styleArr.length; i++) {
                        if(styleArr[i] && styleArr[i].indexOf(":") != -1){
                            let arr = styleArr[i].split(":");
                            if(arr.length == 2){
                                for (let key in ele.style) {
                                    if(key == arr[0].trim()){
                                        style += (arr[0] + ": " + ele.style[key] + ";");
                                    }
                                }
                            }
                        }
                    }
                    console.log(style);
                    div.attributes['style'].value = style;
                    ele.showHtml = div.innerHTML;
                }
            }
        }
    };
</script>





















