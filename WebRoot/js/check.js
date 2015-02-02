var parentTopHeight;
var parentBottomHeight;
var parentTopHeight_left;
var parentBottomHeight_left;
var fixHeight;
var skinName;
var themeColor = "blue";
var broswerFlag;
var fontSize = 12;
var prePath = "../";
var exitVtab = 0;
var vtabIdx = 0;
var hasIframe = 0;
$(function() {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		var g = window.navigator.userAgent.substring(30, 33);
		if (g == "6.0") {
			broswerFlag = "IE6"
		} else {
			if (g == "7.0") {
				broswerFlag = "IE7"
			} else {
				if (g == "8.0") {
					broswerFlag = "IE8"
				} else {
					if (g == "9.0") {
						broswerFlag = "IE9"
					}
				}
			}
		}
	} else {
		if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
			broswerFlag = "Firefox"
		} else {
			if (window.navigator.userAgent.indexOf("Opera") >= 0) {
				broswerFlag = "Opera"
			} else {
				if (window.navigator.userAgent.indexOf("Safari") >= 1) {
					broswerFlag = "Safari"
				} else {
					broswerFlag = "Other"
				}
			}
		}
	}
	var q;
	if ($("#skin").attr("prePath") != null) {
		prePath = $("#skin").attr("prePath")
	}
	if (broswerFlag == "IE6" || broswerFlag == "IE7") {
		if ($(window.top.document.getElementById("skin")).attr("href") == "") {
			q = "skins/sky/import_skin.css"
		} else {
			q = $(window.top.document.getElementById("skin")).attr("href")
		}
	} else {
		try {
			var o = window.top.document.getElementById("skin")
		} catch(e) {
			if ($("body").attr("leftFrame") == "true") {
				alert("如果你当前浏览器是webkit内核（chrome、safari或搜狗的高速模式等），请不要直接在本地打开，而要把此框架发布到web服务目录下访问。")
			}
		}
		if ($(window.top.document.getElementById("skin")).attr("href") == null) {
			q = "skins/sky/import_skin.css"
		} else {
			q = $(window.top.document.getElementById("skin")).attr("href")
		}
	}
	if ($(window.top.document.getElementById("skin")).attr("themeColor") != null) {
		themeColor = $(window.top.document.getElementById("skin")).attr("themeColor")
	}
	var i = q.split("/");
	var f = getPosition("skins", i) + 1;
	skinName = i[f];
	if (broswerFlag == "IE6" || broswerFlag == "IE7") {
		if ($(window.top.document.getElementById("skin")).attr("href") == "") {} else {
			$.ajax({
				url: prePath + "skins/" + skinName + "/import_skin.css",
				error: function() {
					alert("无法通过路径：" + prePath + "skins/" + skinName + "/import_skin.css加载CSS，请检查prePath设置的是否正确。详情请参照“使用帮助>>内页结构及注意事项”")
				}
			})
		}
	} else {
		if ($(window.top.document.getElementById("skin")).attr("href") == null) {} else {
			$.ajax({
				url: prePath + "skins/" + skinName + "/import_skin.css",
				error: function() {
					alert("无法通过路径：" + prePath + "skins/" + skinName + "/import_skin.css加载CSS，请检查prePath设置的是否正确。详情请参照“使用帮助>>内页结构及注意事项”")
				}
			})
		}
	}
	$("#skin").attr("href", prePath + "skins/" + skinName + "/import_skin.css");
	try {
		var r = jQuery.jCookie("fontSize");
		if (r != false) {
			fontSize = parseInt(r)
		}
	} catch(v) {}
	if (fontSize != 12) {
		$("body").css({
			fontSize: fontSize + "px"
		});
		if ($("table:[class=tableStyle]").length > 0) {
			$("table:[class=tableStyle]").css({
				fontSize: fontSize + "px"
			})
		}
		if ($("pre").length > 0) {
			$("pre").css({
				fontSize: fontSize + "px"
			})
		}
	}
	if ($(".box1").length > 0) {
		$(".box1").each(function() {
			var e = $(this).html();
			$(this).html("");
			if ($(this).attr("whiteBg") == "true") {
				$("<div class='box1_topcenter2'><div class='box1_topleft2'><div class='box1_topright2'></div></div></div>").appendTo($(this));
				$("<div class='box1_middlecenter'><div class='box1_middleleft2'><div class='box1_middleright2'><div class='boxContent'></div></div></div></div>").appendTo($(this));
				$("<div class='box1_bottomcenter2'><div class='box1_bottomleft2'><div class='box1_bottomright2'></div></div></div>").appendTo($(this))
			} else {
				$("<div class='box1_topcenter'><div class='box1_topleft'><div class='box1_topright'></div></div></div>").appendTo($(this));
				$("<div class='box1_middlecenter'><div class='box1_middleleft'><div class='box1_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
				$("<div class='box1_bottomcenter'><div class='box1_bottomleft'><div class='box1_bottomright'></div></div></div>").appendTo($(this))
			}
			if ($(this).attr("panelWidth") != null) {
				var E = $(this).attr("panelWidth");
				var D = E.substr(E.length - 1, 1);
				if (D == "%") {
					$(this).width(E)
				} else {
					var F = Number($(this).attr("panelWidth"));
					$(this).width(F)
				}
			}
			if ($(this).attr("panelHeight") != null) {
				$(this).find(".box1_topcenter").height(20);
				$(this).find(".box1_bottomcenter").height(22);
				var C = Number($(this).attr("panelHeight")) - $(this).find(".box1_topcenter").outerHeight() - $(this).find(".box1_bottomcenter").outerHeight();
				$(this).find(".boxContent").height(C)
			}
			$(this).find(".boxContent").html(e);
			if ($(this).attr("overflow") != null) {
				$(this).find(".boxContent").css({
					overflow: $(this).attr("overflow")
				})
			}
		})
	}
	if ($(".box2").length > 0) {
		var m = $("<img style='display:none;'/>");
		m.attr("src", prePath + "skins/" + skinName + "/box/box2TopFix.gif");
		$("body").append(m);
		var x = $("<img style='display:none;'/>");
		x.attr("src", prePath + "skins/" + skinName + "/box/box2BottomFix.gif");
		$("body").append(x);
		var a = $("<img style='display:none;'/>");
		a.attr("src", prePath + "skins/" + skinName + "/box/box2BottomFix2.gif");
		$("body").append(a);
		var b = m.height();
		var h = x.height();
		var j = a.height();
		$(".box2").each(function() {
			var E = $(this).html();
			$(this).html("");
			$("<div class='box2_topcenter'><div class='box2_topleft'><div class='box2_topright'><div class='title'></div><div class='status'><span class='ss'><a></a></span></div><div class='clear'></div></div></div></div>").appendTo($(this));
			$("<div class='box2_middlecenter'><div class='box2_middleleft'><div class='box2_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
			var I = $("<div class='box2_bottomcenter' id='box2_bottomcenter'><div class='box2_bottomleft'><div class='box2_bottomright'></div></div></div>");
			var H = $("<div class='box2_bottomcenter2' id='box2_bottomcenter'><div class='box2_bottomleft2'><div class='box2_bottomright2'></div></div></div>");
			if ($(this).attr("roller") == "false") {
				I.appendTo($(this))
			} else {
				H.appendTo($(this))
			}
			if ($(this).attr("panelTitle") != null) {
				$(this).find(".title").append($(this).attr("panelTitle"))
			}
			if ($(this).attr("panelWidth") != null) {
				var C = $(this).attr("panelWidth");
				var K = C.substr(C.length - 1, 1);
				if (K == "%") {
					$(this).width(C)
				} else {
					var e = Number($(this).attr("panelWidth"));
					$(this).width(e)
				}
			}
			if ($(this).attr("panelHeight") != null) {
				if (broswerFlag != "Safari") {
					$(this).find(".box2_topcenter").height(b);
					$(this).find(".box2_bottomcenter").height(h);
					$(this).find(".box2_bottomcenter2").height(j)
				}
				var L = Number($(this).attr("panelHeight")) - $(this).find(".box2_topcenter").outerHeight() - $(this).find("#box2_bottomcenter").outerHeight();
				$(this).find(".boxContent").height(L)
			}
			$(this).find(".boxContent").html(E);
			if ($(this).attr("overflow") != null) {
				$(this).find(".boxContent").css({
					overflow: $(this).attr("overflow")
				})
			}
			var F = "true";
			if ($(this).attr("showStatus") != null) {
				F = $(this).attr("showStatus")
			}
			var J = "#";
			if ($(this).attr("panelUrl") != null) {
				J = $(this).attr("panelUrl")
			}
			var G = "_self";
			if ($(this).attr("panelTarget") != null) {
				G = $(this).attr("panelTarget")
			}
			var D = "收缩";
			if ($(this).attr("statusText") != null) {
				D = $(this).attr("statusText")
			}
			var M;
			if (D == "收缩" && F == "true") {
				$(this).find(".ss").text(D);
				$(this).find(".ss").toggle(function() {
					var N = $(this).parents(".box2").find(".boxContent");
					M = N.height();
					if (broswerFlag == "IE6") {
						N.fadeOut(300, resetHeight)
					} else {
						N.hide(300, resetHeight)
					}
					$(this).text("展开")
				},
				function() {
					var N = $(this).parents(".box2").find(".boxContent");
					N.height(M);
					if (broswerFlag == "IE6") {
						N.fadeIn(300, resetHeight)
					} else {
						N.show(300, resetHeight)
					}
					if ($(this).parents(".box2").attr("panelHeight") == null) {
						setTimeout(function() {
							N.css({
								height: "auto"
							})
						},
						500)
					}
					$(this).text("收缩")
				})
			} else {
				if (F == "true" || $(this).attr("statusText") != null) {
					$(this).find(".ss").find("a").attr("href", J);
					$(this).find(".ss").find("a").attr("target", G);
					$(this).find(".ss").find("a").text(D)
				} else {
					$(this).find(".ss").hide()
				}
			}
		})
	}
	if ($(".box3").length > 0) {
		$(".box3").each(function() {
			var E = $(this).html();
			$(this).html("");
			$("<div class='box3_topcenter'><div class='box3_topleft'><div class='box3_topright'><div class='title'></div><div class='status'><span class='ss'><a></a></span></div><div class='clear'></div></div></div></div>").appendTo($(this));
			$("<div class='box3_middlecenter'><div class='box3_middleleft'><div class='box3_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
			var H = $("<div class='box3_bottomcenter'><div class='box3_bottomleft'><div class='box3_bottomright'></div></div></div>");
			H.appendTo($(this));
			if ($(this).attr("panelTitle") != null) {
				$(this).find(".title").append($(this).attr("panelTitle"))
			}
			if ($(this).attr("panelWidth") != null) {
				var C = $(this).attr("panelWidth");
				var J = C.substr(C.length - 1, 1);
				if (J == "%") {
					$(this).width(C)
				} else {
					var e = Number($(this).attr("panelWidth"));
					$(this).width(e)
				}
			}
			if ($(this).attr("panelHeight") != null) {
				$(this).find(".box3_topcenter").height(29);
				$(this).find(".box3_bottomcenter").height(2);
				var K = Number($(this).attr("panelHeight")) - $(this).find(".box3_topcenter").outerHeight() - $(this).find(".box3_bottomcenter").outerHeight();
				$(this).find(".boxContent").height(K)
			}
			$(this).find(".boxContent").html(E);
			if ($(this).attr("overflow") != null) {
				$(this).find(".boxContent").css({
					overflow: $(this).attr("overflow")
				})
			}
			var F = "false";
			if ($(this).attr("showStatus") != null) {
				F = $(this).attr("showStatus")
			}
			var I = "#";
			if ($(this).attr("panelUrl") != null) {
				I = $(this).attr("panelUrl")
			}
			var G = "_self";
			if ($(this).attr("panelTarget") != null) {
				G = $(this).attr("panelTarget")
			}
			var D = "更多>>";
			if ($(this).attr("statusText") != null) {
				D = $(this).attr("statusText")
			}
			var L;
			if (D == "收缩") {
				$(this).find(".ss").text(D);
				$(this).find(".ss").toggle(function() {
					var M = $(this).parents(".box3").find(".boxContent");
					L = M.height();
					M.hide(300, resetHeight);
					if ($(this).parents(".box3").attr("panelHeight") == null) {
						setTimeout(function() {
							M.css({
								height: "auto"
							})
						},
						500)
					}
					$(this).text("展开")
				},
				function() {
					var M = $(this).parents(".box3").find(".boxContent");
					M.height(L);
					M.show(300, resetHeight);
					$(this).text("收缩")
				})
			} else {
				if (F == "true" || $(this).attr("statusText") != null) {
					$(this).find(".ss").find("a").attr("href", I);
					$(this).find(".ss").find("a").attr("target", G);
					$(this).find(".ss").find("a").text(D)
				} else {
					$(this).find(".ss").hide()
				}
			}
		})
	}
	if ($(".box4").length > 0) {
		$(".box4").each(function() {
			var e = $(this).html();
			$(this).html("");
			if ($(this).attr("noTitle") == "true") {
				$("<div class='box4_topcenter2'><div class='box4_topleft2'><div class='box4_topright2'></div></div></div>").appendTo($(this))
			} else {
				$("<div class='box4_topcenter'><div class='box4_topleft'><div class='box4_topright'><div class='title'></div></div></div></div>").appendTo($(this))
			}
			$("<div class='box4_middlecenter'><div class='box4_middleleft'><div class='box4_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
			$("<div class='box4_bottomcenter'><div class='box4_bottomleft'><div class='box4_bottomright'></div></div></div>").appendTo($(this));
			if ($(this).attr("panelTitle") != null) {
				$(this).find(".title").append($(this).attr("panelTitle"))
			}
			if ($(this).attr("panelWidth") != null) {
				var E = $(this).attr("panelWidth");
				var D = E.substr(E.length - 1, 1);
				if (D == "%") {
					$(this).width(E)
				} else {
					var F = Number($(this).attr("panelWidth"));
					$(this).width(F)
				}
			}
			if ($(this).attr("panelHeight") != null) {
				$(this).find(".box4_topcenter").height(27);
				$(this).find(".box4_bottomcenter").height(5);
				var C = Number($(this).attr("panelHeight")) - $(this).find(".box4_topcenter").outerHeight() - $(this).find(".box4_bottomcenter").outerHeight();
				$(this).find(".boxContent").height(C)
			}
			$(this).find(".boxContent").html(e);
			if ($(this).attr("overflow") != null) {
				$(this).find(".boxContent").css({
					overflow: $(this).attr("overflow")
				})
			}
		})
	}
	if ($("#vtabConIn").length > 0) {
		exitVtab = 1;
		try {
			var p = jQuery.jCookie("vtabIndex");
			if (p != false) {
				vtabIdx = parseInt(p)
			}
			$(".list_menu2").not(":eq(" + vtabIdx + ")").hide();
			$(".vtab >div").eq(vtabIdx).addClass("vtab_cur");
			$(".vtab >div").each(function(e) {
				$(this).click(function() {
					$(".vtab >div").removeClass("vtab_cur");
					$(this).addClass("vtab_cur");
					jQuery.jCookie("vtabIndex", e.toString());
					$(".list_menu2").hide();
					$(".list_menu2").eq(e).slideDown(600,
					function() {
						$(".list_menu2").not(":eq(" + e + ")").hide()
					})
				})
			})
		} catch(v) {}
	}
	if ($(".list_menu2").length > 0) {
		try {
			$(".list_menu2").each(function() {
				if ($(this).attr("showAll") != "true") {
					$(this).find(".child").hide();
					$(this).find(".parent").each(function() {
						$(this).find("a").eq(0).click(function() {
							$(this).parents(".list_menu2").find(".child").hide();
							if (broswerFlag == "IE6" || broswerFlag == "IE7") {
								$(this).parent().find("ul").slideDown()
							} else {
								$(this).parent().next("ul").slideDown()
							}
						})
					})
				}
				$(this).find("dt").find("a").click(function() {
					$(this).parents(".list_menu2").find("dt").find("a").removeClass("current");
					$(this).addClass("current")
				})
			})
		} catch(v) {}
	}
	if ($(".list_menu3").length > 0) {
		$("#scrollContent").css({
			position: "static"
		});
		try {
			$(".list_menu3 >div span").each(function() {
				$(this).click(function() {
					$(".list_menu3 >div").removeClass("current");
					$(this).parent("div").addClass("current")
				});
				$(this).hover(function() {
					$(this).animate({
						paddingLeft: "40px"
					},
					"fast")
				},
				function() {
					$(this).animate({
						paddingLeft: "20px"
					})
				})
			})
		} catch(v) {}
	}
	if ($(".list_menu3_min").length > 0) {
		$("#scrollContent").css({
			position: "static"
		});
		try {
			$(".list_menu3_min >div span").each(function() {
				$(this).click(function() {
					$(".list_menu3_min >div").removeClass("current");
					$(this).parent("div").addClass("current")
				});
				$(this).hover(function() {
					$(this).animate({
						paddingLeft: "30px"
					},
					"fast")
				},
				function() {
					$(this).animate({
						paddingLeft: "10px"
					})
				})
			})
		} catch(v) {}
	}
	if ($(".date").length > 0) {
		var l = document.createElement("script");
		l.type = "text/javascript";
		l.src = prePath + "My97DatePicker/WdatePicker.js";
		document.body.appendChild(l);
		$(".date").each(function() {
			var e = "yyyy-MM-dd";
			if ($(this).attr("dateFmt") != null) {
				e = $(this).attr("dateFmt")
			}
			$(this).focus(function() {
				WdatePicker()
			})
		})
	}
	$("input:file[class='']").addClass("file");
	$("input:file[class='file']").rebrushfileupload();
	var o;
	$("input:text[class=''],input:password[class=''],input:text[class*=validate],input:password[class*=validate]").each(function() {
		$(this).addClass("textinput");
		$(this).hover(function() {
			if (o != $(this)[0]) {
				$(this).removeClass("textinput");
				$(this).addClass("textinput_hover")
			}
		},
		function() {
			if (o != $(this)[0]) {
				$(this).removeClass("textinput_hover");
				$(this).addClass("textinput")
			}
		});
		$(this).focus(function() {
			o = $(this)[0];
			$(this).removeClass("textinput");
			$(this).removeClass("textinput_hover");
			$(this).addClass("textinput_click")
		});
		$(this).blur(function() {
			o = null;
			$(this).removeClass("textinput_click");
			$(this).addClass("textinput")
		});
		if ($(this).attr("clearable") == "true") {
			$(this).clearableTextField()
		}
		if ($(this).attr("maxNum") != null) {
			$(this).maxlength()
		}
		if ($(this).attr("checkStrength") == "true") {
			$(this).password_strength()
		}
		if ($(this).attr("watermark") != null) {
			$(this).watermark("watermark", $(this).attr("watermark"))
		}
	});
	$("input:password[class='textinput'],input:password[class*=validate]").each(function() {
		$(this).caps(function(e) {
			if (jQuery.browser.safari) {
				return
			}
			if (e) {
				$.cursorMessage("注意：大写键开启了")
			} else {}
		})
	});
	$("input:text[class='date'],input:text[class='cusDate'],input:text[class='keypad'],input:text[class='color']").each(function() {
		$(this).hover(function() {
			if (o != $(this)[0]) {
				$(this).addClass("date_hover")
			}
		},
		function() {
			if (o != $(this)[0]) {
				$(this).removeClass("date_hover")
			}
		});
		$(this).focus(function() {
			o = $(this)[0];
			$(this).removeClass("date_hover");
			$(this).addClass("date_click")
		});
		$(this).blur(function() {
			o = null;
			$(this).removeClass("date_click")
		})
	});
	$("textarea").each(function() {
		if ($(this).attr("class") == "") {
			$(this).addClass("textarea")
		}
		if ($(this).attr("maxNum") != null) {
			$(this).maxlength({
				maxCharacters: parseInt($(this).attr("maxNum"))
			})
		}
		if ($(this).attr("resize") == "true") {
			$(this).TextAreaResizer()
		}
		if ($(this).attr("autoHeight") == "true") {
			$(this).css({
				height: "auto"
			});
			$(this).attr("rows", 5);
			$(this).autoGrow()
		}
		if ($(this).attr("watermark") != null) {
			$(this).watermark("watermark", $(this).attr("watermark"))
		}
	});
	$("textarea[class='textarea'],textarea[class*='textarea'],textarea[class*=validate]").each(function() {
		$(this).hover(function() {
			if (o != $(this)[0]) {
				$(this).removeClass("textarea");
				$(this).addClass("textarea_hover")
			}
		},
		function() {
			if (o != $(this)[0]) {
				$(this).removeClass("textarea_hover");
				$(this).addClass("textarea")
			}
		});
		$(this).focus(function() {
			o = $(this)[0];
			$(this).removeClass("textarea");
			$(this).removeClass("textarea_hover");
			$(this).addClass("textarea_click")
		});
		$(this).blur(function() {
			o = null;
			$(this).removeClass("textarea_click");
			$(this).addClass("textarea")
		})
	});
	$("button").each(function() {
		if ($(this).attr("class") == "") {
			$(this).addClass("button");
			var C = _getStrLength($(this).text());
			if (C < 5) {
				$(this).width(60)
			}
			var e = 0;
			var D = 50;
			e = _getStrLength($(this).filter(":has(span)").find("span").text());
			if (e != 0) {
				D = 20 + 7 * e + 10
			}
			if (broswerFlag == "Firefox" || broswerFlag == "Opera" || broswerFlag == "Safari") {
				$(this).filter(":has(span)").css({
					paddingLeft: "5px",
					width: D + 8 + "px"
				})
			} else {
				$(this).filter(":has(span)").css({
					paddingLeft: "5px",
					width: D + "px"
				})
			}
			$(this).filter(":has(span)").find("span").css({
				cursor: "default"
			})
		}
	});
	$("input:button[class=''],input:submit[class=''],input:reset[class='']").each(function() {
		$(this).addClass("button");
		var e = _getStrLength($(this).val());
		if (e < 5) {
			$(this).width(60)
		}
	});
	$("input:button[class='button'],input:submit[class='button'],input:reset[class='button'],button[class='button']").each(function() {
		$(this).hover(function() {
			if (o != $(this)[0]) {
				$(this).removeClass("button");
				$(this).addClass("button_hover")
			}
		},
		function() {
			if (o != $(this)[0]) {
				$(this).removeClass("button_hover");
				$(this).addClass("button")
			}
		});
		$(this).focus(function() {
			$(this).removeClass("button");
			$(this).addClass("button_hover")
		});
		$(this).blur(function() {
			$(this).removeClass("button_hover");
			$(this).addClass("button")
		})
	});
	$(".render input:checkbox[class='']").custCheckBox();
	$(".render input:radio[class='']").custCheckBox();
	$("select").each(function() {
		if ($(this).attr("class") == "" && $(this).attr("multiple") == false) {
			$(this).selectbox()
		}
	});
	$("select[class*=validate]").not("[multiple]").selectbox();
	if ($(".img_light").length > 0) {
		$(".img_light").addClass("hand");
		$(".img_light").hover(function() {
			$(this).removeClass("img_light");
			$(this).addClass("img_lightOn")
		},
		function() {
			$(this).addClass("img_light");
			$(this).removeClass("img_lightOn")
		})
	}
	enableTooltips();
	if ($(".cusTreeTable").length > 0) {
		$(".cusTreeTable").each(function() {
			$(this).find("tr").filter(":has(table)").hide();
			if ($(this).attr("trClick") == "true") {
				$(this).find("tr").eq(0).nextAll().not(":has(table)").each(function() {
					$(this).addClass("hand");
					$(this).hover(function() {
						$(this).addClass("highlight")
					},
					function() {
						$(this).removeClass("highlight")
					});
					$(this).click(function() {
						if ($(this).next().css("display") == "none") {
							$(this).next().show();
							if ($(this).parents("table").attr("ohterClose") != "false") {
								$(this).parents("table").find(".img_remove2").attr("title", "点击展开");
								$(this).parents("table").find(".img_remove2").addClass("img_add2");
								$(this).parents("table").find(".img_remove2").removeClass("img_remove2");
								$(this).next().nextAll().filter(":has(table)").hide();
								$(this).next().prevAll().filter(":has(table)").hide()
							}
							$(this).find(".img_add2").each(function() {
								$(this).attr("title", "点击收缩");
								$(this).removeClass("img_add2");
								$(this).addClass("img_remove2")
							})
						} else {
							$(this).next().hide();
							$(this).find(".img_remove2").each(function() {
								$(this).removeClass("img_remove2");
								$(this).addClass("img_add2");
								$(this).attr("title", "点击展开")
							})
						}
						enableTooltips();
						hideTooltip()
					})
				})
			} else {
				$(this).find(".img_add2").click(function() {
					if ($(this).parents("tr").next().css("display") == "none") {
						$(this).parents("tr").next().show();
						if ($(this).parents("table").attr("ohterClose") != "false") {
							$(this).parents("table").find(".img_remove2").attr("title", "点击展开");
							$(this).parents("table").find(".img_remove2").addClass("img_add2");
							$(this).parents("table").find(".img_remove2").removeClass("img_remove2");
							$(this).parents("tr").next().nextAll().filter(":has(table)").hide();
							$(this).parents("tr").next().prevAll().filter(":has(table)").hide()
						}
						$(this).attr("title", "点击收缩");
						$(this).removeClass("img_add2");
						$(this).addClass("img_remove2")
					} else {
						$(this).parents("tr").next().hide();
						$(this).removeClass("img_remove2");
						$(this).addClass("img_add2");
						$(this).attr("title", "点击展开")
					}
					enableTooltips();
					hideTooltip()
				})
			}
		})
	}
	if ($(".simpleTab").length > 0) {
		$(".simpleTab").each(function() {
			$(this).find(".simpleTab_con").not(":eq(0)").hide();
			$(this).find(".simpleTab_top li").each(function(e) {
				$(this).click(function() {
					$(this).parent().find("li").removeClass("current");
					$(this).addClass("current");
					if ($(this).parents(".simpleTab").attr("iframeMode") != "true") {
						$(this).parents(".simpleTab").find(".simpleTab_con").hide();
						$(this).parents(".simpleTab").find(".simpleTab_con").eq(e).fadeIn()
					}
				})
			})
		})
	}
	if ($(".simpleMenu").length > 0) {
		refreshSimpleMenu()
	}
	if ($("#scrollContent").length > 0) {
		$("#scrollContent").css({
			overflowX: "hidden"
		});
		$("body").addClass("trans_bg");
		parentTopHeight = $(window.parent.document.getElementById("hbox")).outerHeight() + $(window.parent.document.getElementById("rbox_topcenter")).outerHeight() + parseInt($(window.parent.document.getElementById("rbox")).css("paddingTop")) + parseInt($(window.parent.document.getElementById("rbox")).css("paddingBottom"));
		parentBottomHeight = $(window.parent.document.getElementById("fbox")).outerHeight() + $(window.parent.document.getElementById("rbox_bottomcenter")).outerHeight();
		parentTopHeight_left = $(window.parent.document.getElementById("hbox")).outerHeight() + $(window.parent.document.getElementById("lbox_topcenter")).outerHeight() + parseInt($(window.parent.document.getElementById("lbox")).css("paddingTop")) + parseInt($(window.parent.document.getElementById("lbox")).css("paddingBottom"));
		parentBottomHeight_left = $(window.parent.document.getElementById("fbox")).outerHeight() + $(window.parent.document.getElementById("lbox_bottomcenter")).outerHeight();
		if (parentTopHeight > 0) {
			if ($("body").attr("leftFrame") == "true") {
				$("body").addClass("contentStyleLeft")
			} else {
				$("body").addClass("contentStyle")
			}
			$("#scrollContent").css({
				overflowY: "auto"
			})
		}
		getFixHeight();
		scrollContent();
		var A = null;
		if (document.all) {} else {
			window.onload = function() {
				resetHeight()
			}
		}
		var k = 0;
		var B = 0;
		if ($("table:[class=tableStyle]", "#scrollContent").length > 0) {
			var d = 0;
			var n = $("table:[class=tableStyle]", "#scrollContent").eq(0);
			var u;
			if ($("table:[class=tableStyle]").length > 1) {
				u = $("table:[class=tableStyle]").eq(0);
				if (u.attr("headFixMode") == "true") {
					d = 1;
					n.css({
						borderTop: 0
					});
					u.addClass("noBottomLine")
				}
			}
			if (n.height() > $("#scrollContent").height()) {
				if (broswerFlag == "IE6") {
					k = 1
				} else {
					if (broswerFlag == "IE7") {
						B = 1
					}
				}
				if (d == 1) {
					if (broswerFlag != "IE6") {
						var z = u.find("th").last();
						var w = z.width();
						z.width(w + 17)
					}
				}
			}
			setTableStyle()
		} else {
			if ($(".flexiStyle", "#scrollContent").length > 0) {
				$("#scrollContent").css({
					overflowY: "hidden",
					overflowX: "hidden"
				});
				$(".contentStyle").css({
					paddingRight: "8px"
				})
			}
		}
		if ($(".box1,.box2,.box3", "#scrollContent").length > 0) {
			$(".box1,.box2,.box3").each(function() {
				var e = $(this).attr("panelWidth");
				if (e == "100%" || e == null) {
					if (broswerFlag == "IE6") {
						k = 1
					} else {
						if (broswerFlag == "IE7") {
							B = 1
						}
					}
				}
			})
		}
		if (k == 1) {
			setTimeout(s, 500)
		}
		if (B == 1) {
			setTimeout(c, 100)
		}
		function s() {
			var C = $("body").css("paddingRight");
			var e = parseInt(C) + 17;
			$("body").css({
				paddingRight: e + "px"
			})
		}
		function c() {
			$("#scrollContent").css({
				paddingRight: "17px"
			})
		}
	} else {
		if ($("body").attr("rel") == "layout") {
			$("body").addClass("trans_bg");
			setTableStyle();
			parentTopHeight = $(window.parent.document.getElementById("hbox")).outerHeight() + $(window.parent.document.getElementById("rbox_topcenter")).outerHeight();
			parentBottomHeight = $(window.parent.document.getElementById("fbox")).outerHeight() + $(window.parent.document.getElementById("rbox_bottomcenter")).outerHeight() + 1;
			var t = window.parent.document.documentElement.clientHeight;
			try {
				window.top.document.getElementsByTagName("iframe")["frmright"].style.height = t - parentTopHeight - parentBottomHeight + "px"
			} catch(v) {}
		} else {
			setTableStyle();
			$("body").addClass("zDialogCon");
			if (broswerFlag == "IE6") {
				var y = $("body").width();
				$("body").width(y - 17)
			}
		}
	}
});
function refreshSimpleMenu() {
	$(".simpleMenu").hover(function() {
		if ($(this).find(".simpleMenu_link").attr("noBorder") != "true") {
			$(this).find(".simpleMenu_link").addClass("hoverBorder")
		}
		$(this).find(".simpleMenu_con").show()
	},
	function() {
		$(this).find(".simpleMenu_link").removeClass("hoverBorder");
		$(this).find(".simpleMenu_con").hide()
	})
}
function getFixHeight() {
	fixHeight = 0;
	$("#scrollContent").parent().find(">*").not("div").not("#btc").hide();
	$("#scrollContent").parent().find(">div").not("#scrollContent").not(".searchMain").not(".jquery_rgbmultiselect_options_container").not("#cursorMessageDiv").not(".simplemenu").not(".iconmenu").not(".megamenu").not(".b-m-mpanel").each(function() {
		if ($(this).css("display") != "none") {
			fixHeight = fixHeight + $(this).outerHeight();
			if ($(this).css("marginBottom") != "auto") {
				fixHeight = fixHeight + parseInt($(this).css("marginBottom"))
			}
			if ($(this).css("marginTop") != "auto") {
				fixHeight = fixHeight + parseInt($(this).css("marginTop"))
			}
		}
	})
}
function scrollContent() {
	try {
		var b = window.top.document.documentElement.clientHeight;
		if (parentTopHeight > 0) {
			if ($("body").attr("leftFrame") == "true") {
				$("#scrollContent").height(b - parentTopHeight_left - parentBottomHeight_left - fixHeight)
			} else {
				$("#scrollContent").height(b - parentTopHeight - parentBottomHeight - fixHeight)
			}
			if ($(".flexiStyle").length > 0) {
				var a = b - parentTopHeight - parentBottomHeight - fixHeight - 45;
				$(".bDiv").height(a)
			}
		}
	} catch(c) {}
	if ($("body").attr("leftFrame") == "true") {
		try {
			window.top.document.getElementsByTagName("iframe")["frmleft"].style.height = b - parentTopHeight_left - parentBottomHeight_left + "px"
		} catch(c) {}
	} else {
		try {
			window.top.document.getElementsByTagName("iframe")["frmright"].style.height = b - parentTopHeight - parentBottomHeight + "px"
		} catch(c) {}
	}
	if (exitVtab == 1) {
		try {
			$("#vtabConIn").height(b - parentTopHeight_left - parentBottomHeight_left);
			$(".vtab").height(b - parentTopHeight_left - parentBottomHeight_left - 20)
		} catch(c) {}
	}
}
function resetHeight() {
	try {
		getFixHeight();
		scrollContent()
	} catch(a) {}
}
function changeFont(a) {
	$("body").css({
		fontSize: a + "px"
	});
	if ($("table:[class=tableStyle]").length > 0) {
		$("table:[class=tableStyle]").css({
			fontSize: a + "px"
		})
	}
	if ($("pre").length > 0) {
		$("pre").css({
			fontSize: a + "px"
		})
	}
	if ($("iframe").length > 0) {
		for (var b = 0; b < $("iframe").length; b++) {
			document.getElementsByTagName("iframe")[b].contentWindow.changeFont(a)
		}
	}
}
function setTableStyle() {
	$(".tableStyle").each(function() {
		$(this).find("th").addClass("th");
		if ($(this).find("tr").eq(1).find("td").eq(0).find('input[type="checkbox"]').length == 1) {
			if ($(this).attr("useCheckBox") != "false") {
				$(this).attr("useCheckBox", "true")
			}
			if ($(this).attr("useMultColor") != "false") {
				$(this).attr("useMultColor", "true")
			}
		}
		if ($(this).find("tr").eq(1).find("td").eq(0).find('input[type="radio"]').length == 1) {
			if ($(this).attr("useRadio") != "false") {
				$(this).attr("useRadio", "true")
			}
		}
		if ($(this).attr("formMode") == "true") {
			$(this).attr("useColor", "false");
			$(this).attr("useHover", "false");
			$(this).attr("useClick", "false");
			$(this).find("th").css({
				fontWeight: "bold",
				"text-align": "center"
			});
			$(this).find("tr").not("tr:last").find("td:even").css("text-align", "right");
			if ($(this).attr("footer") != null) {
				if ($(this).attr("footer") == "left") {
					$(this).find("tr:last").find("td").css("text-align", "left")
				} else {
					if ($(this).attr("footer") == "right") {
						$(this).find("tr:last").find("td").css("text-align", "right")
					} else {
						if ($(this).attr("footer") == "center") {
							$(this).find("tr:last").find("td").css("text-align", "center")
						} else {
							if ($(this).attr("footer") == "normal") {
								$(this).find("tr:last").find("td:even").css("text-align", "right")
							}
						}
					}
				}
			} else {
				$(this).find("tr:last").find("td").css("text-align", "center")
			}
			$(this).find("td").css({
				paddingTop: "3px",
				paddingBottom: "3px"
			})
		}
		if ($(this).attr("transMode") == "true") {
			$(this).attr("useColor", "false");
			$(this).attr("useHover", "false");
			$(this).attr("useClick", "false");
			$(this).find("th").css({
				fontWeight: "bold",
				"text-align": "center"
			});
			$(this).css({
				border: "none",
				backgroundColor: "transparent"
			});
			$(this).find("tr").css({
				border: "none",
				backgroundColor: "transparent"
			});
			$(this).find("tr").not("tr:last").find("td:even").css("text-align", "right");
			if ($(this).attr("footer") != null) {
				if ($(this).attr("footer") == "left") {
					$(this).find("tr:last").find("td").css("text-align", "left")
				} else {
					if ($(this).attr("footer") == "right") {
						$(this).find("tr:last").find("td").css("text-align", "right")
					} else {
						if ($(this).attr("footer") == "center") {
							$(this).find("tr:last").find("td").css("text-align", "center")
						} else {
							if ($(this).attr("footer") == "normal") {
								$(this).find("tr:last").find("td:even").css("text-align", "right")
							}
						}
					}
				}
			} else {
				$(this).find("tr:last").find("td").css("text-align", "center")
			}
			$(this).find("td").css({
				paddingTop: "3px",
				paddingBottom: "3px",
				border: "none"
			})
		}
		if ($(this).attr("useColor") != "false") {
			$(this).find("tr:even").addClass("odd")
		}
		if ($(this).attr("useHover") != "false") {
			$(this).find("tr").hover(function() {
				$(this).addClass("highlight")
			},
			function() {
				$(this).removeClass("highlight")
			})
		}
		if ($(this).attr("sortMode") == "true") {
			$(this).find("th").filter(":has(span)").hover(function() {
				$(this).removeClass("th");
				$(this).addClass("th_over")
			},
			function() {
				$(this).removeClass("th_over");
				$(this).addClass("th")
			});
			$(this).find("th span").addClass("sort_off");
			$(this).find("th").click(function() {})
		}
		if ($(this).attr("useClick") != "false") {
			$(this).attr("useClick", "true")
		}
		if ($(this).attr("useClick") == "true" && $(this).attr("useMultColor") == "true") {
			$(this).attr("useClick", "false")
		}
		if ($(this).attr("useRadio") != "true") {
			$(this).attr("useRadio", "false")
		}
		if ($(this).attr("useCheckBox") != "true") {
			$(this).attr("useCheckBox", "false")
		}
		if ($(this).attr("useClick") != "false") {
			if ($(this).attr("useRadio") == "false") {
				$(this).find("tr").click(function() {
					$(this).siblings().removeClass("selected");
					$(this).addClass("selected")
				})
			} else {
				$(this).find('input[type="radio"]:checked').parents("tr").addClass("selected");
				$(this).find("tr").click(function() {
					$(this).siblings().removeClass("selected");
					$(this).addClass("selected");
					$(this).find('input[type="radio"]').attr("checked", "checked")
				})
			}
		}
		if ($(this).attr("useMultColor") == "true") {
			if ($(this).attr("useCheckBox") == "false") {
				$(this).find("tr").click(function() {
					$(this).toggleClass("selected")
				})
			} else {
				$(this).find('input[type="checkbox"]:checked').parents("tr").addClass("selected");
				if ($(this).find("th").length > 0) {
					var a = $('<span class="img_checkAllOff" title="点击全选"></span>');
					$(this).find("th").eq(0).html("");
					$(this).find("th").eq(0).append(a);
					try {
						enableTooltips()
					} catch(b) {}
					if ($(this).attr("headFixMode") == "true") {
						a.toggle(function() {
							$("table:[class=tableStyle]").find("tr").each(function() {
								$(this).addClass("selected");
								$(this).find('input[type="checkbox"]').attr("checked", "checked")
							});
							$(this).removeClass("img_checkAllOff");
							$(this).addClass("img_checkAllOn");
							$(this).attr("title", "取消全选");
							try {
								hideTooltip();
								enableTooltips()
							} catch(c) {}
						},
						function() {
							$("table:[class=tableStyle]").find("tr").each(function() {
								if ($(this).hasClass("selected")) {
									$(this).removeClass("selected");
									$(this).find('input[type="checkbox"]').removeAttr("checked")
								}
							});
							$(this).removeClass("img_checkAllOn");
							$(this).addClass("img_checkAllOff");
							$(this).attr("title", "点击全选");
							try {
								hideTooltip();
								enableTooltips()
							} catch(c) {}
						})
					} else {
						a.toggle(function() {
							$(this).parents("table").find("tr").each(function() {
								$(this).addClass("selected");
								$(this).find('input[type="checkbox"]').attr("checked", "checked")
							});
							$(this).removeClass("img_checkAllOff");
							$(this).addClass("img_checkAllOn");
							$(this).attr("title", "取消全选");
							try {
								hideTooltip();
								enableTooltips()
							} catch(c) {}
						},
						function() {
							$(this).parents("table").find("tr").each(function() {
								if ($(this).hasClass("selected")) {
									$(this).removeClass("selected");
									$(this).find('input[type="checkbox"]').removeAttr("checked")
								}
							});
							$(this).removeClass("img_checkAllOn");
							$(this).addClass("img_checkAllOff");
							$(this).attr("title", "点击全选");
							try {
								hideTooltip();
								enableTooltips()
							} catch(c) {}
						})
					}
				}
				$(this).find("tr:has(td)").click(function() {
					if ($(this).hasClass("selected")) {
						$(this).removeClass("selected");
						$(this).find('input[type="checkbox"]').removeAttr("checked")
					} else {
						$(this).addClass("selected");
						$(this).find('input[type="checkbox"]').attr("checked", "checked")
					}
				})
			}
		}
	})
}
function getPosition(b, c) {
	for (var a = 0; a < c.length; a++) {
		if (b == c[a]) {
			return a;
			break
		}
	}
} (function(a) {
	a.fn.custCheckBox = function(b) {
		var d = {
			disable_all: false,
			hover: true,
			wrapperclass: "group",
			callback: function() {}
		};
		var c = a.extend(d, b);
		return this.each(function() {
			var e = a(this);
			a.fn.buildbox = function(f) {
				if (broswerFlag == "IE6" || broswerFlag == "IE7" || broswerFlag == "IE8") {
					a(f).css({
						display: "none"
					}).before('<span class="cust_checkbox">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>')
				} else {
					a(f).css({
						display: "none"
					}).before('<span class="cust_checkbox">&nbsp;&nbsp;&nbsp;</span>')
				}
				var i = a(f).attr("checked");
				var g = a(f).attr("type");
				var h = a(f).attr("disabled");
				if (g === "checkbox") {
					a(f).prev("span").addClass("checkbox");
					if (h || c.disable_all) {
						g = "checkbox_disabled"
					}
				} else {
					a(f).prev("span").addClass("radio");
					if (h || c.disable_all) {
						g = "radio_disabled"
					}
				}
				if (i) {
					a(f).prev("span").addClass("cust_" + g + "_on")
				} else {
					a(f).prev("span").addClass("cust_" + g + "_off")
				}
				if (c.disable_all) {
					a(f).attr("disabled", "disabled")
				}
				a(f).prev("span").prev("label").css({
					cursor: "pointer"
				});
				a(f).prev("span").prev("label").unbind().click(function() {
					if (a(f).attr("onclick") != null) {
						a(a(f).attr("onclick"))
					}
					if (!c.disable_all) {
						var l = a(this).next("span");
						var j = a(l).next("input").attr("type");
						var k = a(l).next("input").attr("disabled");
						if (a(l).hasClass("checkbox")) {
							if (a(l).hasClass("cust_" + j + "_off") && !k) {
								if (broswerFlag == "IE6" || broswerFlag == "IE7" || broswerFlag == "IE8") {
									a(l).removeClass("cust_" + j + "_off").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_on").next("input").attr("checked", "checked")
								} else {
									a(l).removeClass("cust_" + j + "_off").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_on").next("input").removeAttr("checked")
								}
							} else {
								if (!k) {
									if (broswerFlag == "IE6" || broswerFlag == "IE7" || broswerFlag == "IE8") {
										a(l).removeClass("cust_" + j + "_on").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_off").next("input").removeAttr("checked")
									} else {
										a(l).removeClass("cust_" + j + "_on").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_off").next("input").attr("checked", "checked")
									}
									a(l).removeClass("cust_" + j + "_hvr")
								}
							}
						} else {
							if (!k) {
								a(l).parent().find(".cust_checkbox").removeClass("cust_" + j + "_on").addClass("cust_" + j + "_off").next("input").removeAttr("checked");
								a(l).removeClass("cust_" + j + "_off").addClass("cust_" + j + "_on").next("input").attr("checked", "checked");
								a(l).removeClass("cust_" + j + "_hvr")
							}
						}
						c.callback.call(this)
					}
				}).hover(function() {
					var j = a(this).next("span");
					if (a(j).hasClass("cust_checkbox_off") && c.hover) {
						a(j).addClass("cust_checkbox_hvr")
					} else {
						if (a(j).hasClass("cust_radio_off") && c.hover) {
							a(j).addClass("cust_radio_hvr")
						}
					}
				},
				function() {
					var j = a(this).next("span");
					if (a(j).hasClass("cust_checkbox_off") && c.hover) {
						a(j).removeClass("cust_checkbox_hvr")
					} else {
						if (a(j).hasClass("cust_radio_off") && c.hover) {
							a(j).removeClass("cust_radio_hvr")
						}
					}
				});
				a(f).prev("span").unbind().click(function() {
					if (a(f).attr("onclick") != null) {
						a(a(f).attr("onclick"))
					}
					if (!c.disable_all) {
						var j = a(this).next("input").attr("type");
						var k = a(this).next("input").attr("disabled");
						if (a(this).hasClass("checkbox")) {
							if (a(this).hasClass("cust_" + j + "_off") && !k) {
								a(this).removeClass("cust_" + j + "_off").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_on").next("input").attr("checked", "checked")
							} else {
								if (!k) {
									a(this).removeClass("cust_" + j + "_on").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_off").next("input").removeAttr("checked");
									a(this).removeClass("cust_" + j + "_hvr")
								}
							}
						} else {
							if (!k) {
								a(this).parent().find(".cust_checkbox").removeClass("cust_" + j + "_on").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_off").next("input").removeAttr("checked");
								a(this).removeClass("cust_" + j + "_off").removeClass("cust_" + j + "_hvr").addClass("cust_" + j + "_on").next("input").attr("checked", "checked")
							}
						}
						c.callback.call(this)
					}
				}).hover(function() {
					if (a(this).hasClass("cust_checkbox_off") && c.hover) {
						a(this).addClass("cust_checkbox_hvr")
					} else {
						if (a(this).hasClass("cust_radio_off") && c.hover) {
							a(this).addClass("cust_radio_hvr")
						}
					}
				},
				function() {
					if (a(this).hasClass("cust_checkbox_off") && c.hover) {
						a(this).removeClass("cust_checkbox_hvr")
					} else {
						if (a(this).hasClass("cust_radio_off") && c.hover) {
							a(this).removeClass("cust_radio_hvr")
						}
					}
				})
			};
			a.fn.buildbox(a(e))
		})
	}
})(jQuery);
function radioRefresh(a) {
	var b;
	if (typeof(a) == "object") {
		b = a
	} else {
		b = $("#" + a)
	}
	b.find("span").remove();
	b.find("input:radio[class='']").custCheckBox()
}
function checkRefresh(a) {
	var b;
	if (typeof(a) == "object") {
		b = a
	} else {
		b = $("#" + a)
	}
	b.find("span").remove();
	b.find("input:checkbox[class='']").custCheckBox()
}
jQuery.fn.extend({
	selectbox: function(a) {
		return this.each(function() {
			new jQuery.SelectBox(this, a)
		})
	}
});
if (!window.console) {
	var console = {
		log: function(a) {}
	}
}
var depth = 500;
var elm_id = 1;
jQuery.SelectBox = function(F, g) {
	var c = g || {};
	c.inputClass = c.inputClass || "selectbox";
	c.containerClass = c.containerClass || "selectbox-wrapper";
	c.hoverClass = c.hoverClass || "current";
	c.currentClass = c.selectedClass || "selected";
	c.debug = c.debug || false;
	elm_id++;
	var i = 0;
	var e = false;
	var D = 0;
	var A = $(F);
	var x = k(c);
	var p = B();
	var d = q(c);
	var w = false;
	var v = false;
	var j = 1;
	var E;
	var C;
	var G = 0;
	if (window.navigator.userAgent.indexOf("Windows") > -1) {
		G = 1
	}
	C = A.width();
	if (C == "0") {
		C = 116
	}
	var n;
	if (G == 1) {
		if (broswerFlag == "Safari") {
			n = $("<input type='button' value=' ' class='selBtn_safari'/>")
		} else {
			if (broswerFlag != null && broswerFlag == 'IE9') {
				n = $("<input type='button' value=' ' class='selBtnIe9'/>")
			} else {
				n = $("<input type='button' value=' ' class='selBtn'/>")
			}
		}
	} else {
		n = $("<input type='button' value=' ' class='selBtn_linux'/>")
	}
	if (A.attr("disabled") == true) {
		n.attr("disabled", true);
		n.addClass("selBtn_disabled")
	}
	var u = $("<div class='loader'>数据加载中...</div>");
	if (A.attr("autoWidth") != null) {
		if (A.attr("autoWidth") == "true") {
			w = true
		} else {
			w = false
		}
	}
	if (A.attr("colNum") != null) {
		j = parseInt(A.attr("colNum"))
	}
	if (A.attr("colWidth") != null) {
		E = Number(A.attr("colWidth"))
	}
	if (j != 1) {
		if (w) {
			d.width(C - 20)
		} else {
			d.width(96)
		}
		if (E != null) {
			x.width(E * j + 40)
		} else {
			var b = Number(C);
			x.width(b * j + 40)
		}
	} else {
		if (w) {
			d.width(C - 20);
			x.width(C + 6)
		} else {
			d.width(96);
			var f = 96 + 4 + 22;
			var b = Number(C);
			x.width(Math.max(f, b))
		}
	}
	A.hide().before(p);
	p.append(d);
	p.append(n);
	p.append(x);
	p.append(u);
	u.hide();
	y();
	if (A.attr("editable") != null) {
		if (A.attr("editable") == "true") {
			v = true
		} else {
			v = false
		}
	}
	if (!v) {
		d.css({
			cursor: "pointer"
		});
		d.click(function() {
			var L;
			var J = x.find("li").length;
			if (j == 1) {
				L = J * 26
			} else {
				if (J % j == 0) {
					L = J * 26 / j
				} else {
					L = (J - J % j) * 26 / j + 26
				}
			}
			x.height(L);
			var H = 200;
			if (parentTopHeight > 0) {
				var K = window.top.document.documentElement.clientHeight;
				H = K - parentTopHeight - parentBottomHeight - p.offset().top - 30
			} else {
				H = window.document.documentElement.clientHeight - (p.offset().top - $(window).scrollTop()) - 30
			}
			if (H < x.height()) {
				if (p.offset().top > x.height()) {
					if (broswerFlag == "IE8") {
						x.css({
							top: -x.height() - 17
						})
					} else {
						if ($.browser.msie) {
							x.css({
								top: -x.height()
							})
						} else {
							x.css({
								top: -x.height() - 7
							})
						}
					}
				} else {
					if (H < 100 && p.offset().top > H) {
						x.height(p.offset().top);
						x.css({
							overflow: "auto"
						});
						if (broswerFlag == "IE8") {
							x.css({
								top: -x.height() - 17
							})
						} else {
							if ($.browser.msie) {
								x.css({
									top: -x.height()
								})
							} else {
								x.css({
									top: -x.height() - 7
								})
							}
						}
					} else {
						x.css({
							overflow: "auto"
						});
						if (broswerFlag == "IE8") {
							x.css({
								top: 8
							})
						} else {
							if ($.browser.msie) {
								x.css({
									top: 25
								})
							} else {
								x.css({
									top: 18
								})
							}
						}
						x.height(H)
					}
				}
			} else {
				if (broswerFlag == "IE8") {
					x.css({
						top: 8
					})
				} else {
					if ($.browser.msie) {
						x.css({
							top: 25
						})
					} else {
						x.css({
							top: 18
						})
					}
				}
			}
			if (!e) {
				depth++;
				p.css({
					zIndex: depth
				});
				setTimeout(I, 100)
			}
			function I() {
				x.toggle()
			}
		}).focus(function() {
			if (x.not(":visible")) {
				depth++;
				p.css({
					zIndex: depth
				});
				e = true;
				setTimeout(H, 100)
			}
			function H() {
				x.show()
			}
		}).keydown(function(H) {
			switch (H.keyCode) {
			case 38:
				H.preventDefault();
				o( - 1);
				break;
			case 40:
				H.preventDefault();
				o(1);
				break;
			case 13:
				H.preventDefault();
				$("li." + c.hoverClass).trigger("click");
				break;
			case 27:
				l();
				break
			}
		}).blur(function() {
			if (x.is(":visible") && D > 0) {
				if (c.debug) {
					console.log("container visible and has focus")
				}
			} else {
				l()
			}
		})
	} else {
		d.css({
			cursor: "text"
		});
		d.change(function() {
			A.attr("editValue", $(this).val())
		})
	}
	n.click(function() {
		var L;
		var J = x.find("li").length;
		if (j == 1) {
			L = J * 26
		} else {
			if (J % j == 0) {
				L = J * 26 / j
			} else {
				L = (J - J % j) * 26 / j + 26
			}
		}
		x.height(L);
		var I = 200;
		if (parentTopHeight > 0) {
			var K = window.top.document.documentElement.clientHeight;
			I = K - parentTopHeight - parentBottomHeight - p.offset().top - 30
		} else {
			I = window.document.documentElement.clientHeight - (p.offset().top - $(window).scrollTop()) - 30
		}
		if (I < x.height()) {
			if (p.offset().top > x.height()) {
				if (broswerFlag == "IE8") {
					x.css({
						top: -x.height() - 17
					})
				} else {
					if ($.browser.msie) {
						x.css({
							top: -x.height()
						})
					} else {
						x.css({
							top: -x.height() - 7
						})
					}
				}
			} else {
				if (I < 100 & p.offset().top > I) {
					x.height(p.offset().top);
					x.css({
						overflow: "auto"
					});
					if (broswerFlag == "IE8") {
						x.css({
							top: -x.height() - 17
						})
					} else {
						if ($.browser.msie) {
							x.css({
								top: -x.height()
							})
						} else {
							x.css({
								top: -x.height() - 7
							})
						}
					}
				} else {
					x.css({
						overflow: "auto"
					});
					if (broswerFlag == "IE8") {
						x.css({
							top: 8
						})
					} else {
						if ($.browser.msie) {
							x.css({
								top: 25
							})
						} else {
							x.css({
								top: 18
							})
						}
					}
					x.height(I)
				}
			}
		} else {
			if (broswerFlag == "IE8") {
				x.css({
					top: 8
				})
			} else {
				if ($.browser.msie) {
					x.css({
						top: 25
					})
				} else {
					x.css({
						top: 18
					})
				}
			}
		}
		if (!e) {
			depth++;
			p.css({
				zIndex: depth
			});
			setTimeout(H, 100)
		}
		function H() {
			x.toggle()
		}
	}).focus(function() {
		if (x.not(":visible")) {
			depth++;
			p.css({
				zIndex: depth
			});
			e = true;
			setTimeout(H, 100)
		}
		function H() {
			x.show()
		}
	}).keydown(function(H) {
		switch (H.keyCode) {
		case 38:
			H.preventDefault();
			o( - 1);
			break;
		case 40:
			H.preventDefault();
			o(1);
			break;
		case 13:
			H.preventDefault();
			$("li." + c.hoverClass).trigger("click");
			break;
		case 27:
			l();
			break
		}
	}).blur(function() {
		if (x.is(":visible") && D > 0) {
			if (c.debug) {
				console.log("container visible and has focus")
			}
		} else {
			l()
		}
	});
	function l() {
		D = 0;
		x.hide()
	}
	function y() {
		x.append(r(d.attr("id"))).hide();
		var H = d.css("width")
	}
	function B() {
		var H = $("<div></div>");
		H.addClass("mainCon");
		return H
	}
	function k(H) {
		var I = $("<div></div>");
		I.attr("id", elm_id + "_container");
		I.addClass(H.containerClass);
		I.css({});
		return I
	}
	function q(I) {
		var H = document.createElement("input");
		var K = $(H);
		K.attr("id", elm_id + "_input");
		K.attr("type", "text");
		K.addClass(I.inputClass);
		K.attr("autocomplete", "off");
		var J = false;
		if (A.attr("editable") != null) {
			if (A.attr("editable") == "true") {
				J = true
			} else {
				J = false
			}
		}
		if (!J) {
			K.attr("readonly", "readonly")
		} else {
			K.attr("readonly", false)
		}
		K.attr("tabIndex", A.attr("tabindex"));
		if (A.attr("disabled") == true) {
			K.attr("disabled", true);
			K.addClass("inputDisabled")
		}
		return K
	}
	function o(I) {
		var H = $("li", x);
		if (!H || H.length == 0) {
			return false
		}
		i += I;
		if (i < 0) {
			i = H.size()
		} else {
			if (i > H.size()) {
				i = 0
			}
		}
		a(H, i);
		H.removeClass(c.hoverClass);
		$(H[i]).addClass(c.hoverClass)
	}
	function a(I, J) {
		var H = $(I[J]).get(0);
		var I = x.get(0);
		if (H.offsetTop + H.offsetHeight > I.scrollTop + I.clientHeight) {
			I.scrollTop = H.offsetTop + H.offsetHeight - I.clientHeight
		} else {
			if (H.offsetTop < I.scrollTop) {
				I.scrollTop = H.offsetTop
			}
		}
	}
	function h() {
		var H = $("li." + c.currentClass, x).get(0);
		var I = (H.id).split("_");
		var K = I[0].length + I[1].length + 2;
		var L = H.id;
		var J = L.substr(K, L.length);
		A.val(J);
		A.attr("relText", $(H).text());
		d.val($(H).html());
		if (v == true) {
			A.attr("editValue", d.val())
		}
		A.focus();
		return true
	}
	function s() {
		return A.val()
	}
	function m() {
		return d.val()
	}
	function r(N) {
		var O = new Array();
		var K = document.createElement("ul");
		var M = [];
		var I = 0;
		var H;
		if (A.attr("childId") != null) {
			H = true
		}
		var J = 1;
		var L;
		if (A.attr("colNum") != null) {
			J = parseInt(A.attr("colNum"))
		}
		if (A.attr("colWidth") != null) {
			L = Number(A.attr("colWidth"))
		}
		A.find("option").each(function() {
			M.push($(this)[0]);
			var P = document.createElement("li");
			P.setAttribute("id", N + "_" + $(this).val());
			P.innerHTML = $(this).html();
			if ($(this).is(":selected")) {
				var Q;
				if (A.attr("editable") != null) {
					if (A.attr("editable") == "true") {
						Q = true
					} else {
						Q = false
					}
				}
				if (Q == true) {
					d.val("请输入或选择");
					d.addClass("tipColor");
					d.focus(function() {
						if ($(this).val() == "请输入或选择") {
							$(this).val("");
							d.removeClass("tipColor")
						}
					});
					d.blur(function() {
						if ($(this).val() == "") {
							$(this).val("请输入或选择");
							d.addClass("tipColor")
						}
					})
				} else {
					d.val($(this).html());
					$(P).addClass(c.currentClass)
				}
			}
			if (J != 1) {
				$(P).addClass("li_left");
				if (L != null) {
					$(P).width(L)
				} else {
					var R = Number(C);
					$(P).width(R)
				}
			}
			K.appendChild(P);
			$(P).mouseover(function(S) {
				D = 1;
				if (c.debug) {
					console.log("over on : " + this.id)
				}
				jQuery(S.target, x).addClass(c.hoverClass)
			}).mouseout(function(S) {
				D = -1;
				if (c.debug) {
					console.log("out on : " + this.id)
				}
				jQuery(S.target, x).removeClass(c.hoverClass)
			}).click(function(T) {
				var U = $("li." + c.hoverClass, x).get(0);
				if (c.debug) {
					console.log("click on :" + this.id)
				}
				var S = $(this).attr("id").split("_");
				$("#" + S[0] + "_container li." + c.currentClass).removeClass(c.currentClass);
				$(this).addClass(c.currentClass);
				h();
				A.get(0).blur();
				l();
				if (A.attr("onchange") != null) {
					$(A.attr("onchange"))
				}
				d.removeClass("tipColor");
				if (H) {
					t(A, A.val())
				}
			})
		});
		A.find("optgroup").each(function() {
			var Q = getPosition($(this).children("option").eq(0)[0], M);
			var P = $(this).attr("label");
			$(K).find("li").eq(Q + I).before("<li class='group'>" + P + "</li>");
			I++
		});
		return K
	}
	function t(J, I) {
		if (I != "") {
			var K = J.attr("childId");
			var H = $("#" + K).prev().find("div[class=loader]");
			H.show();
			window.setTimeout(function() {
				z(J, I)
			},
			200)
		}
	}
	function z(J, I) {
		var H;
		if (J.attr("childDataType") == null) {
			H = J.attr("childDataPath") + I
		} else {
			if (J.attr("childDataType") == "url") {
				H = J.attr("childDataPath") + I
			} else {
				H = J.attr("childDataPath") + I + "." + J.attr("childDataType")
			}
		}
		$.ajax({
			url: H,
			error: function() {
				try {
					top.Dialog.alert("数据加载失败，请检查childDataPath是否正确")
				} catch(K) {
					alert("数据加载失败，请检查childDataPath是否正确")
				}
			},
			success: function(N) {
				var K = J.attr("childId");
				var S = $("#" + K).prev().find("div[class=loader]");
				S.hide();
				var Q = $("#" + K).prev().find("ul");
				var M = $("#" + K).prev().find(">div").attr("id").split("_")[0];
				var L = $("#" + K).prev().find("input:text");
				var O = $("#" + K)[0];
				Q.html("");
				O.options.length = 0;
				$(N).find("node").each(function() {
					var V = $(this).attr("text");
					var U = $(this).attr("value");
					var T = document.createElement("li");
					$(T).text(V);
					$(T).attr("relValue", U);
					Q.append($(T));
					O.options[O.options.length] = new Option(V, U);
					$(T).mouseover(function(W) {
						jQuery(W.target).addClass(c.hoverClass)
					});
					$(T).mouseout(function(W) {
						jQuery(W.target).removeClass(c.hoverClass)
					});
					$(T).mousedown(function(X) {
						$("#" + M + "_container li." + c.currentClass).removeClass(c.currentClass);
						$(this).addClass(c.currentClass);
						$("#" + K).attr("relText", $(this).text());
						$("#" + K).val($(this).attr("relValue"));
						L.val($(this).html());
						$("#" + K).prev().find(">div").hide();
						$("#" + K).focus();
						if ($("#" + K).attr("onchange") != null) {
							$($("#" + K).attr("onchange"))
						}
						var W;
						if ($("#" + K).attr("childId") != null) {
							W = true
						}
						if (W) {
							t($("#" + K), $("#" + K).val())
						}
					})
				});
				if ($(N).find("node").length == 0) {
					var R = document.createElement("li");
					$(R).text("无内容");
					Q.append($(R))
				}
				var P = Q.find("li").eq(0);
				L.val(P.text());
				P.addClass(c.currentClass);
				$("#" + K).attr("relValue", P.attr("relValue"));
				$("#" + K).attr("relText", P.text())
			}
		})
	}
};
function selRefresh(a) {
	var b;
	if (typeof(a) == "object") {
		b = a
	} else {
		b = $("#" + a)
	}
	b.prev(".mainCon").remove();
	b.selectbox()
}
function enableTooltips(e) {
	var b,
	a,
	c,
	d;
	if (!document.getElementById || !document.getElementsByTagName) {
		return
	}
	AddCss();
	d = document.createElement("span");
	d.id = "btc";
	d.setAttribute("id", "btc");
	d.style.position = "absolute";
	d.style.zIndex = 9999;
	$("body").append($(d));
	$("a[title],span[title],input[title],textarea[title],img[title],div[title]").each(function() {
		if ($(this).attr("defaultTip") != "false") {
			Prepare($(this)[0])
		}
	})
}
function _getStrLength(c) {
	var b;
	var a;
	for (b = 0, a = 0; b < c.length; b++) {
		if (c.charCodeAt(b) < 128) {
			a++
		} else {
			a = a + 2
		}
	}
	return a
}
function Prepare(f) {
	var g,
	d,
	a,
	e,
	c;
	d = f.getAttribute("title");
	if (d != null && d.length != 0) {
		f.removeAttribute("title");
		if (_getStrLength(d) > 37 || _getStrLength(d) == 37) {
			g = CreateEl("span", "tooltip")
		} else {
			if (_getStrLength(d) > 10 && _getStrLength(d) < 37) {
				g = CreateEl("span", "tooltip_mid")
			} else {
				g = CreateEl("span", "tooltip_min")
			}
		}
		e = CreateEl("span", "top");
		$(e).html(d);
		g.appendChild(e);
		a = CreateEl("b", "bottom");
		g.appendChild(a);
		setOpacity(g);
		f.tooltip = g;
		f.onmouseover = showTooltip;
		f.onmouseout = hideTooltip;
		f.onmousemove = Locate
	}
}
function showTooltip(a) {
	document.getElementById("btc").appendChild(this.tooltip);
	Locate(a)
}
function hideTooltip(a) {
	var b = document.getElementById("btc");
	if (b.childNodes.length > 0) {
		b.removeChild(b.firstChild)
	}
}
function setOpacity(a) {
	a.style.filter = "alpha(opacity:95)";
	a.style.KHTMLOpacity = "0.95";
	a.style.MozOpacity = "0.95";
	a.style.opacity = "0.95"
}
function CreateEl(b, d) {
	var a = document.createElement(b);
	a.className = d;
	a.style.display = "block";
	return (a)
}
function AddCss() {}
function Locate(c) {
	var a = 0,
	f = 0;
	if (c == null) {
		c = window.event
	}
	if (c.pageX || c.pageY) {
		a = c.pageX;
		f = c.pageY
	} else {
		if (c.clientX || c.clientY) {
			if (document.documentElement.scrollTop) {
				a = c.clientX + document.documentElement.scrollLeft;
				f = c.clientY + document.documentElement.scrollTop
			} else {
				a = c.clientX + document.body.scrollLeft;
				f = c.clientY + document.body.scrollTop
			}
		}
	}
	document.getElementById("btc").style.top = (f + 10) + "px";
	var d = window.document.documentElement.clientWidth;
	var b = $("#btc").width();
	if (d - b < a - 20) {
		document.getElementById("btc").style.left = (d - b) + "px"
	} else {
		document.getElementById("btc").style.left = (a - 20) + "px"
	}
} (function(c) {
	var h,
	i;
	var d = 0;
	var a = 32;
	var e;
	c.fn.TextAreaResizer = function() {
		return this.each(function() {
			h = c(this).addClass("processed"),
			i = null;
			c(this).wrap('<div class="resizable-textarea"><span></span></div>').parent().append(c('<div class="grippie"></div>').bind("mousedown", {
				el: this
			},
			b));
			var k = c("div.grippie", c(this).parent())[0];
			k.style.marginRight = (k.offsetWidth - c(this)[0].offsetWidth) + "px"
		})
	};
	function b(k) {
		h = c(k.data.el);
		h.blur();
		d = j(k).y;
		i = h.height() - d;
		h.css("opacity", 0.25);
		c(document).mousemove(g).mouseup(f);
		return false
	}
	function g(m) {
		var k = j(m).y;
		var l = i + k;
		if (d >= (k)) {
			l -= 5
		}
		d = k;
		l = Math.max(a, l);
		h.height(l + "px");
		if (l < a) {
			f(m)
		}
		return false
	}
	function f(k) {
		c(document).unbind("mousemove", g).unbind("mouseup", f);
		h.css("opacity", 1);
		h.focus();
		h = null;
		i = null;
		d = 0
	}
	function j(k) {
		return {
			x: k.clientX + document.documentElement.scrollLeft,
			y: k.clientY + document.documentElement.scrollTop
		}
	}
})(jQuery); (function(a) {
	a.fn.watermark = function(b, c) {
		return this.each(function() {
			var e = a(this),
			d;
			e.focus(function() {
				d && !(d = 0) && e.removeClass(b).data("w", 0).val("")
			}).blur(function() { ! e.val() && (d = 1) && e.addClass(b).data("w", 1).val(c)
			}).closest("form").submit(function() {
				d && e.val("")
			});
			e.blur()
		})
	};
	a.fn.removeWatermark = function() {
		return this.each(function() {
			a(this).data("w") && a(this).val("")
		})
	}
})(jQuery);
if (jQuery) { (function(a) {
		a.cursorMessageData = {};
		a(window).ready(function(b) {
			if (a("#cursorMessageDiv").length == 0) {
				a("body").append('<div id="cursorMessageDiv">&nbsp;</div>');
				a("#cursorMessageDiv").hide()
			}
			a("body").mousemove(function(c) {
				a.cursorMessageData.mouseX = c.pageX;
				a.cursorMessageData.mouseY = c.pageY;
				if (a.cursorMessageData.options != undefined) {
					a._showCursorMessage()
				}
			})
		});
		a.extend({
			cursorMessage: function(c, b) {
				if (b == undefined) {
					b = {}
				}
				if (b.offsetX == undefined) {
					b.offsetX = 5
				}
				if (b.offsetY == undefined) {
					b.offsetY = 5
				}
				if (b.hideTimeout == undefined) {
					b.hideTimeout = 1000
				}
				a("#cursorMessageDiv").html(c).fadeIn("slow");
				if (jQuery.cursorMessageData.hideTimeoutId != undefined) {
					clearTimeout(jQuery.cursorMessageData.hideTimeoutId)
				}
				if (b.hideTimeout > 0) {
					jQuery.cursorMessageData.hideTimeoutId = setTimeout(a.hideCursorMessage, b.hideTimeout)
				}
				jQuery.cursorMessageData.options = b;
				a._showCursorMessage()
			},
			hideCursorMessage: function() {
				a("#cursorMessageDiv").fadeOut("slow")
			},
			_showCursorMessage: function() {
				a("#cursorMessageDiv").css({
					top: (a.cursorMessageData.mouseY + a.cursorMessageData.options.offsetY) + "px",
					left: (a.cursorMessageData.mouseX + a.cursorMessageData.options.offsetX)
				})
			}
		})
	})(jQuery)
}
jQuery.fn.caps = function(a) {
	return this.keypress(function(f) {
		var b = f.which ? f.which: (f.keyCode ? f.keyCode: -1);
		var d = f.shiftKey ? f.shiftKey: (f.modifiers ? !!(f.modifiers & 4) : false);
		var g = ((b >= 65 && b <= 90) && !d) || ((b >= 97 && b <= 122) && d);
		a.call(this, g)
	})
};
function iframeHeight(b) {
	var a = document.getElementById(b);
	a.style.height = a.contentWindow.document.body.scrollHeight + "px"
} (function(a) {
	a.rebrushfileupload = {
		defaults: {
			button_text: " ",
			class_container: "fileupload-rebrush",
			class_field: "fileupload-rebrush-field",
			class_button: "fileupload-rebrush-button"
		}
	};
	a.fn.extend({
		rebrushfileupload: function(d) {
			d = a.extend({},
			a.rebrushfileupload.defaults, d);
			var e = ["padding-left", "padding-right", "margin-left", "margin-right", "border-left-width", "border-right-width"];
			a(this).wrap('<div class="file-container"/>');
			var g = a(this).parent();
			g.prepend('<input type="text" value="" readonly="readonly" /><input type="button" class="fileBtn" value="' + d.button_text + '" />');
			var i = g.find("input[type=text]");
			var b = g.find("input[type=button]");
			var f = 0;
			for (var h in e) {
				var c = Math.round(parseFloat(i.css(e[h]) + 0)) + 0;
				var j = Math.round(parseFloat(b.css(e[h]) + 0)) + 0;
				f += (isNaN(c) ? 0: c) + (isNaN(j) ? 0: j)
			}
			f += Math.round(parseFloat(i.width())) + Math.round(parseFloat(b.width()));
			if (a.browser.msie) {
				i.width(a(this).width() - 65);
				g.css({
					position: "relative",
					width: a(this).width() + 10,
					overflow: "hidden"
				})
			} else {
				i.width(a(this).width() - 90);
				g.css({
					position: "relative",
					width: a(this).width(),
					overflow: "hidden"
				})
			}
			if (broswerFlag != "IE8") {
				a(this).css({
					position: "absolute",
					"z-index": 2,
					"font-size": "12px",
					opacity: "0",
					left: "0px",
					top: "0px"
				})
			} else {
				a(this).css({
					position: "absolute",
					"z-index": 2,
					"font-size": "12px",
					opacity: "0",
					left: "0px",
					top: "-18px"
				})
			}
			a(this).change(function() {
				a(this).parent().find("input[type=text]").val(a(this).val());
				if (a(this).attr("showInfo") != "false") {
					try {
						a(this).attr("title", a(this).val());
						enableTooltips()
					} catch(k) {}
				}
			})
		}
	})
})(jQuery); (function(d) {
	d.fn.clearableTextField = function() {
		if (d(this).length > 0) {
			d(this).bind("keyup change paste cut", e);
			for (var f = 0; f < d(this).length; f++) {
				c(d(d(this)[f]))
			}
		}
	};
	function e() {
		c(d(this))
	}
	function c(f) {
		if (f.val().length > 0) {
			b(f)
		} else {
			a(f)
		}
	}
	function b(i) {
		if (!i.next().hasClass("text_clear_button")) {
			i.after("<div class='text_clear_button'></div>");
			var f = i.next();
			var g = f.outerHeight(),
			k = f.outerHeight();
			i.css("padding-right", parseInt(i.css("padding-right")) + g + 1);
			i.width(i.width() - g - 1);
			var m = i.position();
			var j = {};
			j.left = m.left + i.outerWidth(false) - (g + 2);
			var l = Math.round((i.outerHeight(true) - k) / 2);
			j.top = m.top + d("#scrollContent").scrollTop() + l;
			f.css(j);
			f.click(function() {
				i.val("");
				c(i)
			})
		}
	}
	function a(h) {
		var f = h.next();
		if (f.hasClass("text_clear_button")) {
			f.remove();
			var g = f.width();
			h.css("padding-right", parseInt(h.css("padding-right")) - g - 1);
			h.width(h.width() + g + 1)
		}
	}
})(jQuery); (function(a) {
	a.fn.maxlength = function(b) {
		var c = jQuery.extend({
			events: [],
			maxCharacters: 10,
			status: true,
			statusClass: "maxNum",
			statusText: "剩余字数",
			notificationClass: "notification",
			showAlert: false,
			alertText: "输入字符超出限制.",
			slider: true
		},
		b);
		a.merge(c.events, ["keyup"]);
		return this.each(function() {
			var g = a(this);
			var j = a(this).val().length;
			function d() {
				var k = c.maxCharacters - j;
				if (k < 0) {
					k = 0
				}
				g.next("div").html(c.statusText + " :" + k)
			}
			function e() {
				var k = true;
				if (j >= c.maxCharacters) {
					k = false;
					g.addClass(c.notificationClass);
					g.val(g.val().substr(0, c.maxCharacters));
					i()
				} else {
					if (g.hasClass(c.notificationClass)) {
						g.removeClass(c.notificationClass)
					}
				}
				if (c.status) {
					d()
				}
			}
			function i() {
				if (c.showAlert) {
					alert(c.alertText)
				}
			}
			function f() {
				var k = false;
				if (g.is("textarea")) {
					k = true
				} else {
					if (g.filter("input[type=text]")) {
						k = true
					} else {
						if (g.filter("input[type=password]")) {
							k = true
						}
					}
				}
				return k
			}
			if (!f()) {
				return false
			}
			a.each(c.events,
			function(k, l) {
				g.bind(l,
				function(m) {
					j = g.val().length;
					e()
				})
			});
			if (c.status) {
				g.after(a("<div/>").addClass(c.statusClass).html("-"));
				d()
			}
			if (!c.status) {
				var h = g.next("div." + c.statusClass);
				if (h) {
					h.remove()
				}
			}
			if (c.slider) {
				g.next().hide();
				g.focus(function() {
					g.next().slideDown("fast")
				});
				g.blur(function() {
					g.next().slideUp("fast")
				})
			}
		})
	}
})(jQuery);
var colsDefault = 0;
var rowsDefault = 5;
function setDefaultValues(a) {
	colsDefault = a.cols;
	rowsDefault = $(a).attr("rows")
}
function bindEvents(a) {
	a.onkeyup = function() {
		grow(a)
	}
}
function grow(d) {
	var c = 0;
	var a = d.value.split("\n");
	for (var b = a.length - 1; b >= 0; --b) {
		c += Math.floor((a[b].length / colsDefault) + 1)
	}
	if (c >= rowsDefault) {
		d.rows = c + 1
	} else {
		d.rows = rowsDefault
	}
}
jQuery.fn.autoGrow = function() {
	return this.each(function() {
		setDefaultValues(this);
		bindEvents(this)
	})
}; (function(b) {
	var a = new
	function() {
		this.countRegexp = function(d, e) {
			var c = d.match(e);
			return c ? c.length: 0
		};
		this.getStrength = function(i, e) {
			var c = i.length;
			if (c < e) {
				return 0
			}
			var g = this.countRegexp(i, /\d/g),
			j = this.countRegexp(i, /[a-z]/g),
			f = this.countRegexp(i, /[A-Z]/g),
			d = c - g - j - f;
			if (g == c || j == c || f == c || d == c) {
				return 1
			}
			var h = 0;
			if (g) {
				h += 2
			}
			if (j) {
				h += f ? 4: 3
			}
			if (f) {
				h += j ? 4: 3
			}
			if (d) {
				h += 5
			}
			if (c > 10) {
				h += 1
			}
			return h
		};
		this.getStrengthLevel = function(e, c) {
			var d = this.getStrength(e, c);
			switch (true) {
			case(d <= 0) : return 1;
				break;
			case (d > 0 && d <= 4) : return 2;
				break;
			case (d > 4 && d <= 8) : return 3;
				break;
			case (d > 8 && d <= 12) : return 4;
				break;
			case (d > 12) : return 5;
				break
			}
			return 1
		}
	};
	b.fn.password_strength = function(c) {
		var d = b.extend({
			container: null,
			minLength: 6,
			texts: {
				1: "非常弱",
				2: "弱密码",
				3: "强度一般",
				4: "强密码",
				5: "非常强"
			}
		},
		c);
		return this.each(function() {
			if (d.container) {
				var e = b(d.container)
			} else {
				var e = b("<span/>").attr("class", "password_strength");
				b(this).after(e)
			}
			b(this).keyup(function() {
				var g = b(this).val();
				if (g.length > 0) {
					var h = a.getStrengthLevel(g, d.minLength);
					var f = "password_strength_" + h;
					if (!e.hasClass(f) && h in d.texts) {
						e.text(d.texts[h]).attr("class", "password_strength " + f)
					}
				} else {
					e.text("").attr("class", "password_strength")
				}
			})
		})
	}
})(jQuery);
jQuery.jCookie = function(i, b, l, j) {
	if (!navigator.cookieEnabled) {
		return false
	}
	var j = j || {};
	if (typeof(arguments[0]) !== "string" && arguments.length === 1) {
		j = arguments[0];
		i = j.name;
		b = j.value;
		l = j.expires
	}
	i = encodeURI(i);
	if (b && (typeof(b) !== "number" && typeof(b) !== "string" && b !== null)) {
		return false
	}
	var e = j.path ? "; path=" + j.path: "";
	var f = j.domain ? "; domain=" + j.domain: "";
	var d = j.secure ? "; secure": "";
	var g = "";
	if (b || (b === null && arguments.length == 2)) {
		l = (l === null || (b === null && arguments.length == 2)) ? -1: l;
		if (typeof(l) === "number" && l != "session" && l !== undefined) {
			var k = new Date();
			k.setTime(k.getTime() + (l * 24 * 60 * 60 * 1000));
			g = ["; expires=", k.toGMTString()].join("")
		}
		document.cookie = [i, "=", encodeURI(b), g, f, e, d].join("");
		return true
	}
	if (!b && typeof(arguments[0]) === "string" && arguments.length == 1 && document.cookie && document.cookie.length) {
		var a = document.cookie.split(";");
		var h = a.length;
		while (h--) {
			var c = a[h].split("=");
			if (jQuery.trim(c[0]) === i) {
				return decodeURI(c[1])
			}
		}
	}
	return false
};
function showProgressBar(c) {
	var a = "正在加载中...";
	if (c) {
		a = c
	}
	var b = new top.Dialog();
	b.Width = 360;
	b.Height = 70;
	b.Title = a;
	b.InvokeElementId = "progress";
	b.show()
};