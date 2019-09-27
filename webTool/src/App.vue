<template>
    <div id="app">
        <home-screen></home-screen>
    </div>
</template>

<script>
    import HomeScreen from "@/components/HomeScreen";
    export default {
        name: 'App',
        components:{
            HomeScreen
        },
        created() {
            this.$store.dispatch('setCommonVal',{
                key: 'innerHeight',
                val: window.innerHeight
            });
            this.$store.dispatch('setCommonVal',{
                key: 'innerWidth',
                val: window.innerWidth
            });
        }
    }
</script>

<style scoped>
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
