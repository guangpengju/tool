<template>
    <el-container>
        <!-- 顶部工具条 -->
        <el-header class="main-el-header">
            <tool-bar>
                <template #main>
                    <el-button size="small" @click="showInfo">新建</el-button>
                    <el-button size="small">导入</el-button>
                    <el-button size="small" @click="showLeftAside = true">组件</el-button>
                    <el-button size="small" @click="drawerShow = false">编辑</el-button>
                </template>
                <template #left>
                    <el-button size="small">VUE编辑</el-button>
                    <el-button size="small" @click="drawerShow = true">样式编辑</el-button>
                    <el-switch
                            v-model="drawerShow"
                            inactive-text="编辑">
                    </el-switch>
                    <el-button size="small">源码</el-button>
                    <el-button type="text" icon="el-icon-view" @click="showView">预览</el-button>
                </template>
            </tool-bar>
        </el-header>

        <el-container>
            <!-- 左侧弹框 -->
            <mu-flex>
                <mu-slide-left-transition>
                    <el-aside v-if="showLeftAside" width="200px" class="main-el-aside">
                        <widgets-layout @hidden="showLeftAside = false"/>
                    </el-aside>
                </mu-slide-left-transition>
            </mu-flex>

            <!-- 主布局区 -->
            <el-main>
                <main-layout  />
                <view-layout />
            </el-main>

            <el-aside width="25px" >
                <side-tool-bar :btnList="sideBtnList" @click="sideToolBarClick"/>
            </el-aside>

            <!-- 右侧弹框 -->
            <edit-drawer :drawerShow.sync="drawerShow" />
        </el-container>
    </el-container>
</template>

<script>
    import ToolBar from '@/components/gui/ToolBar'
    import MainLayout from '@/components/layout/MainLayout'
    import WidgetsLayout from '@/components/layout/WidgetsLayout'
    import EditDrawer from "@/components/editConfig/EditDrawer";
    import SideToolBar from "@/components/layout/SideToolBar";
    import ViewLayout from "@/components/layout/ViewLayout";

    export default {
        data() {
            return {
                drawerShow: false,
                showLeftAside: true,
                assemblyHeight: window.innerHeight - 62 + 'px',
                sideBtnList:[{
                    id:'style',
                    name:'样式编辑',
                    icon:'el-icon-edit'
                },{
                    id:'props',
                    name:'属性编辑',
                    icon:'el-icon-edit'
                },{
                    id:'event',
                    name:'事件编辑',
                    icon:'el-icon-edit'
                }]
            }
        },
        components: {
            ViewLayout,
            SideToolBar,
            ToolBar,
            MainLayout,
            WidgetsLayout,
            EditDrawer
        },
        methods: {
            showInfo(){
                console.log(this.$store.state.layout)
            },
            sideToolBarClick(id, choose){
                if(id == 'style'){
                    this.drawerShow = choose;
                }
            },
            showView(){
                this.$store.state.showView = true;
            }
        }
    }
</script>

<style>
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

    .full-screen {
        height: 100%;
        width: 100%;
        margin: 0px;
        padding: 0px;
    }
</style>
