<template>
    <div id="app">
        <el-container>
            <el-header style="background: #F7F7F7;border:1px solid #AAAAAA;">
                <tool-bar></tool-bar>
            </el-header>

            <el-container>
                <el-aside width="200px" style="background: #F3F3F3;border-width: 0px 1px 1px 1px;border-style: solid;border-color: #AAAAAA;">
                    <assembly :height="assemblyHeight"></assembly>
                </el-aside>

                <el-main>
                    <layout></layout>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import ToolBar from '@/components/ToolBar'
    import Assembly from '@/components/Assembly'
    import Layout from '@/components/Layout'

    export default {
        name: 'App',
        data() {
            return {
                assemblyHeight: window.innerHeight - 62 + 'px'
            }
        },
        components: {
            ToolBar,
            Assembly,
            Layout
        },
        methods: {
            router(path) {
                if (this.nav != path) {
                    this.$router.push(path);
                } else {
                    alert("重复了")
                }
            },
            dragulaTest() {
                this.router("element")
            }
        }
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
</style>
