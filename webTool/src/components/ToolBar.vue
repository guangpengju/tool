<template>
    <el-row type="flex" align="middle" style="height: 100%;">
        <el-col :span="2" class="toolbar-logo">
            <span style="font-size: x-large;font-family: LiSu;margin: 20px 0px">布 局 <br/> 工 具</span>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="13" class="toolbar-main">
            <slot name="main"/>
        </el-col>
        <el-col :span="8" class="toolbar-left">
            <slot name="left"/>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: 'ToolBar',
        componentName: 'ToolBar',
        methods:{

        }
    };
</script>

<style>
    .toolbar-logo{
        display: flex;
        justify-content: center;
    }

    .toolbar-main{

    }

    .toolbar-left{
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }
</style>





















