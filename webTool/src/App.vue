<template>
    <div id="app">
        <el-container>
            <!-- 顶部工具条 -->
            <el-header class="main-el-header">
                <tool-bar>
                    <template #main>
                        <el-button size="small">新建</el-button>
                        <el-button size="small">导入</el-button>
                        <el-button size="small" @click="showLeftAside = true">组件</el-button>
                        <el-button size="small" @click="drawerShow = false">编辑</el-button>
                    </template>
                    <template #left>
                        <el-button size="small">VUE编辑</el-button>
                        <el-button size="small" @click="drawerShow = true">样式编辑</el-button>
                        <el-button size="small">源码</el-button>
                    </template>
                </tool-bar>
            </el-header>

            <el-container>
                <mu-flex>
                    <mu-slide-left-transition>
                        <el-aside v-if="showLeftAside" width="200px" class="main-el-aside">
                            <assembly :height="assemblyHeight" @hiddenAssembly="showLeftAside = false"></assembly>
                        </el-aside>
                    </mu-slide-left-transition>
                </mu-flex>

                <el-main>
                    <layout :drawerShow.sync="drawerShow"></layout>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import ToolBar from '@/components/ToolBar'
    import Assembly from '@/components/Assembly'
    import Layout from "@/components/Layout";

    export default {
        name: 'App',
        data() {
            return {
                drawerShow: false,
                showLeftAside: true,
                assemblyHeight: window.innerHeight - 62 + 'px'
            }
        },
        components: {
            Layout,
            ToolBar,
            Assembly
        },
        methods: {}
    }
</script>

<style>
    body {
        margin: 0px;
        padding: 0px;
    }

    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        margin: 0px;
        padding: 0px;
    }

    .main-el-header {
        background: #F7F7F7;
        border:1px solid #AAAAAA;
    }

    .main-el-aside {
        background: #F3F3F3;
        border-width: 0px 1px 1px 1px;
        border-style: solid;
        border-color: #AAAAAA;
    }
</style>
