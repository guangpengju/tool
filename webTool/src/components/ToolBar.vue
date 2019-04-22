<template>
    <el-row type="flex" align="middle" style="height: 100%;">
        <el-col :span="2" style="display: flex;justify-content: center;">
            <span style="font-size: x-large;font-family: LiSu;">布 局 <br/> 工 具</span>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="18">
            <el-button size="small" @click="show">新建</el-button>
            <el-button size="small">导入</el-button>
            <el-button size="small">添加</el-button>
            <el-button size="small">编辑</el-button>
            <el-button size="small">编辑</el-button>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: 'ToolBar',
        componentName: 'ToolBar',
        methods:{
            show(){
                this.$store.commit("show");
            }
        }
    };
</script>





















