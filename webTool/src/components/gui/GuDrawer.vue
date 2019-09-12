<template>
    <div>
        <mu-flex>
            <component :is="slideType">
                <div v-show="open" :class="GUDrawerClass" :style="GUDrawerStyle">
                    <div class="gu-drawer-header">
                        <div style="width: 85%;display: flex;justify-content: center">
                            <span class="gu-drawer-header-title">样式编辑</span>
                        </div>
                        <div style="width: 15%;display: flex;justify-content: flex-end;">
                            <mu-icon size="20" value=":el-icon-close" style="margin-right: 10px"
                                     @click="drawerClose"></mu-icon>
                        </div>
                    </div>

                    <div class="gu-drawer-body">
                        <slot></slot>
                    </div>
                </div>
            </component>
        </mu-flex>
    </div>
</template>

<script>
    export default {
        name: 'GuDrawer',
        componentName: 'GuDrawer',
        data() {
            return {
                open: false,
                defaultClose: false
            }
        },
        computed: {
            GUDrawerClass() {
                let GUDrawerClass = ['gu-drawer-base'];
                GUDrawerClass.push(this.customeClass ? this.customeClass : 'gu-drawer-' + this.drawerType)
                return GUDrawerClass
            },
            GUDrawerStyle() {
                return {
                    width: this.width,
                    height: this.height
                }
            },
            slideType(){
                return "mu-slide-" + this.drawerType + "-transition";
            }
        },
        props: {
            drawerShow: Boolean,
            drawerType: String,
            width: {
                type: String,
                default: "400px"
            },
            height: {
                type: String,
                default: "90%"
            },
            customeClass: String
        },
        methods: {
            drawerClose() {
                this.defaultClose = true;
                this.$emit('update:drawerShow', false);
                this.$emit('drawerClose');
                this.open = false;
            }
        },
        watch: {
            drawerShow(val) {
                if (val) {
                    this.defaultClose = false;
                    this.$emit('drawerOpen');
                } else {
                    if (this.defaultClose) this.$emit('drawerClose');
                }
                this.open = val;
            }
        }
    };
</script>

<style>
    .gu-drawer-base {
        background: #ffffff;
        overflow: hidden;
        z-index: 9999;
        position: fixed;
        border: 2px solid #F7F7F7;
    }

    .gu-drawer-right {
        top: 60px;
        right: 0px;
        padding-right: 3px;
    }

    .gu-drawer-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #AAAAAA;
    }

    .gu-drawer-header-title {
        font-size: 16px;
        color: #72767b;
        font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
        font-weight: 400;
        line-height: 50px;
    }

    .gu-drawer-body {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        margin: 10px 10px 5px 10px;
        /*align-items: stretch;*/
    }
</style>




















