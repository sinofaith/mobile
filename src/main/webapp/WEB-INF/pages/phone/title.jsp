<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<span class="tab_nav">
    <span class="dropdown">
        <a href="#" onclick="skip('a11','phone')" <c:if test="${flag=='a11'||flag=='a12'||flag=='a13'||flag=='a14'}">class="addactive"</c:if> id="dropdownMenu3" data-toggle="dropdown">手机</a>
        <span class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="width: 288px; background: white; margin-top: 8px; margin-left: -288px">
            <li role="presentation">
                <a id="a11" role="menuitem" tabindex="1" href="JavaScript:void(0);" onclick="skip('a11','phone')" style="width: 265px; <c:if test="${flag=='a11'}">background: #09CEB8</c:if>">机主信息</a>
            </li>
            <li role="presentation">
                <a id="a12" role="menuitem" tabindex="2" href="JavaScript:void(0);" onclick="skip('a12','phoneTxl')" style="width: 265px; <c:if test="${flag=='a12'}">background: #09CEB8</c:if>">通讯录</a>
            </li>
            <li role="presentation">
                <a id="a13" role="menuitem" tabindex="3" href="JavaScript:void(0);" onclick="skip('a13','phoneThqd')" style="width: 265px; <c:if test="${flag=='a13'}">background: #09CEB8</c:if>">通话清单</a>
            </li>
            <li role="presentation">
                <a id="a14" role="menuitem" tabindex="4" href="JavaScript:void(0);" onclick="skip('a14','phoneDx')" style="width: 265px; <c:if test="${flag=='a14'}">background: #09CEB8</c:if>">短信信息</a>
            </li>
        </span>
    </span>
    <span class="dropdown">
        <a href="#" onclick="skip('a1','phoneZhxx')" <c:if test="${flag=='a1'||flag=='a2'||flag=='a3'||flag=='a4'||flag=='a5'}">class="addactive"</c:if> id="dropdownMenu1" data-toggle="dropdown">QQ</a>
        <span class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="width: 288px; background: white; margin-top: 8px; margin-left: -288px">
            <li role="presentation">
                <a id="a1" role="menuitem" tabindex="1" href="JavaScript:void(0);" onclick="skip('a1','phoneZhxx')" style="width: 265px; <c:if test="${flag=='a1'}">background: #09CEB8</c:if>">QQ账户信息</a>
            </li>
            <li role="presentation">
                <a id="a2" role="menuitem" tabindex="2" href="JavaScript:void(0);" onclick="skip('a2','phoneqqFriend')" style="width: 265px; <c:if test="${flag=='a2'}">background: #09CEB8</c:if>">QQ好友信息</a>
            </li>
            <li role="presentation">
                <a id="a3" role="menuitem" tabindex="3" href="JavaScript:void(0);" onclick="skip('a3','phoneqqFriends')" style="width: 265px; <c:if test="${flag=='a3'}">background: #09CEB8</c:if>">QQ群好友信息</a>
            </li>
            <li  role="presentation">
                <a id="a4" role="menuitem" tabindex="4" href="JavaScript:void(0);" onclick="skip('a4','phoneqqFriendChat')" style="width: 265px; <c:if test="${flag=='a4'}">background: #09CEB8</c:if>">QQ好友聊天信息</a>
            </li>
            <li role="presentation">
                <a id="a5" role="menuitem" tabindex="5" href="JavaScript:void(0);" onclick="skip('a5','phoneqqFriendsChat')" style="width: 265px; <c:if test="${flag=='a5'}">background: #09CEB8</c:if>" >QQ群好友聊天信息</a>
            </li>
        </span>
    </span>
    <span class="dropdown">
        <a href="#" onclick="skip('a6','phonewxPhone')" <c:if test="${flag=='a6'||flag=='a7'||flag=='a8'||flag=='a9'||flag=='a10'}">class="addactive"</c:if> id="dropdownMenu2" data-toggle="dropdown">微信</a>
        <span class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="width: 288px; background: white; margin-top: 8px; margin-left: -288px">
            <li role="presentation">
                <a id="a6" role="menuitem" tabindex="1" href="JavaScript:void(0);" onclick="skip('a6','phonewxPhone')" style="width: 265px; <c:if test="${flag=='a6'}">background: #09CEB8</c:if>">微信账户信息</a>
            </li>
            <li role="presentation">
                <a id="a7" role="menuitem" tabindex="2" href="JavaScript:void(0);" onclick="skip('a7','phonewxFriend')" style="width: 265px; <c:if test="${flag=='a7'}">background: #09CEB8</c:if>">微信好友信息</a>
            </li>
            <li role="presentation">
                <a id="a8" role="menuitem" tabindex="3" href="JavaScript:void(0);" onclick="skip('a8','phonewxFriends')" style="width: 265px; <c:if test="${flag=='a8'}">background: #09CEB8</c:if>">微信群好友信息</a>
            </li>
            <li role="presentation">
                <a id="a9" role="menuitem" tabindex="4" href="JavaScript:void(0);" onclick="skip('a9','phonewxFriendChat')" style="width: 265px; <c:if test="${flag=='a9'}">background: #09CEB8</c:if>">微信好友聊天信息</a>
            </li>
            <li role="presentation">
                <a id="a10" role="menuitem" tabindex="5" href="JavaScript:void(0);" onclick="skip('a10','phonewxFriendsChat')" style="width: 265px; <c:if test="${flag=='a10'}">background: #09CEB8</c:if>" >微信群好友聊天信息</a>
            </li>
        </span>
    </span>
</span>
<script type="text/javascript">
    function skip(obj,url){
        window.location = "${pageContext.request.contextPath}/"+url+"?flag="+obj;
    }
</script>
