console.log("hello");

const togglesidebar = () =>{
        if($(".slidebar").is(":visible")) {
            $(".slidebar").css("display","none")
            $(".content").css("margin-left","0%")

        }else {
            $(".slidebar").css("display","block")
            $(".content").css("margin-left","20%")
        }
}