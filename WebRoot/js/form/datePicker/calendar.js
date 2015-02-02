var $c;
if ($FF) {
	Event.prototype.__defineSetter__("returnValue",
	function($) {
		if (!$) {
			this.preventDefault();
		}
		return $;
	});
	Event.prototype.__defineGetter__("srcElement",
	function() {
		var $ = this.target;
		while ($.nodeType != 1) {
			$ = $.parentNode;
		}
		return $;
	});
	HTMLElement.prototype.attachEvent = function(_, A) {
		var $ = _.replace(/on/, "");
		A._ieEmuEventHandler = function($) {
			window.event = $;
			return A();
		};
		this.addEventListener($, A._ieEmuEventHandler, false);
	};
}
function My97DP() {
	$c = this;
	this.QS = [];
	$d = document.createElement("div");
	$d.className = "WdateDiv";
	$d.innerHTML = '<div id=dpTitle><div class="navImg NavImgll"><a href="###"></a></div><div class="navImg NavImgl"><a href="###"></a></div><div style="float:left"><div class="menuSel MMenu"></div><input class=yminput tabindex=1></div><div style="float:left"><div class="menuSel YMenu"></div><input class=yminput tabindex=2></div><div class="navImg NavImgrr"><a href="###"></a></div><div class="navImg NavImgr"><a href="###"></a></div><div style="float:right"></div></div><div style="position:absolute;overflow:hidden"></div><div></div><div id=dpTime><div class="menuSel hhMenu"></div><div class="menuSel mmMenu"></div><div class="menuSel ssMenu"></div><table cellspacing=0 cellpadding=0 border=0><tr><td rowspan=2><span id=dpTimeStr></span>&nbsp;<input class=tB maxlength=2 tabindex=3><input value=":" class=tm readonly><input class=tE maxlength=2 tabindex=4><input value=":" class=tm readonly><input class=tE maxlength=2 tabindex=5></td><td><button id=dpTimeUp></button></td></tr><tr><td><button id=dpTimeDown></button></td></tr></table></div><div id=dpQS></div><div id=dpControl><input class=dpButton id=dpClearInput type=button tabindex=6><input class=dpButton id=dpTodayInput type=button tabindex=7><input class=dpButton id=dpOkInput type=button tabindex=8></div>';
	attachTabEvent($d,
	function() {
		hideSel();
	});
	_();
	this.init();
	$();
	_inputBindEvent("y,M,H,m,s");
	$d.upButton.onclick = function() {
		updownEvent(1);
	};
	$d.downButton.onclick = function() {
		updownEvent( - 1);
	};
	$d.qsDiv.onclick = function() {
		if ($d.qsDivSel.style.display != "block") {
			$c._fillQS();
			showB($d.qsDivSel);
		} else {
			hide($d.qsDivSel);
		}
	};
	attachTabEvent($d.okI,
	function() {
		if ($dp.dd.style.display != "none") {
			$d.MI.focus();
		}
		event.returnValue = false;
	});
	document.body.appendChild($d);
	function _() {
		var _ = $("a");
		divs = $("div"),
		ipts = $("input"),
		btns = $("button"),
		spans = $("span");
		$d.navLeftImg = _[0];
		$d.leftImg = _[1];
		$d.rightImg = _[3];
		$d.navRightImg = _[2];
		$d.rMD = divs[9];
		$d.MI = ipts[0];
		$d.yI = ipts[1];
		$d.titleDiv = divs[0];
		$d.MD = divs[4];
		$d.yD = divs[6];
		$d.qsDivSel = divs[10];
		$d.dDiv = divs[11];
		$d.tDiv = divs[12];
		$d.HD = divs[13];
		$d.mD = divs[14];
		$d.sD = divs[15];
		$d.qsDiv = divs[16];
		$d.bDiv = divs[17];
		$d.HI = ipts[2];
		$d.mI = ipts[4];
		$d.sI = ipts[6];
		$d.clearI = ipts[7];
		$d.todayI = ipts[8];
		$d.okI = ipts[9];
		$d.upButton = btns[0];
		$d.downButton = btns[1];
		$d.timeSpan = spans[0];
		function $($) {
			return $d.getElementsByTagName($);
		}
	}
	function $() {
		$d.navLeftImg.onclick = function() {
			$ny = $ny <= 0 ? $ny - 1: -1;
			if ($ny % 5 == 0) {
				$d.yI.focus();
				return;
			}
			$d.yI.value = $dt.y - 1;
			$d.yI.onblur();
		};
		$d.leftImg.onclick = function() {
			$dt.attr("M", -1);
			$d.MI.onblur();
		};
		$d.rightImg.onclick = function() {
			$dt.attr("M", 1);
			$d.MI.onblur();
		};
		$d.navRightImg.onclick = function() {
			$ny = $ny >= 0 ? $ny + 1: 1;
			if ($ny % 5 == 0) {
				$d.yI.focus();
				return;
			}
			$d.yI.value = $dt.y + 1;
			$d.yI.onblur();
		};
	}
}
My97DP.prototype = {
	init: function() {
		$ny = 0;
		$dp.cal = this;
		if ($dp.readOnly && $dp.el.readOnly != null) {
			$dp.el.readOnly = true;
			$dp.el.blur();
		}
		$();
		this.dateFmt = $dp.dateFmt;
		this._dealFmt();
		this.autoPickDate = $dp.autoPickDate == null ? ($dp.has.st && $dp.has.st ? false: true) : $dp.autoPickDate;
		$dt = this.newdate = new DPDate();
		$tdt = new DPDate();
		$sdt = this.date = new DPDate();
		this.ddateRe = this._initRe("disabledDates");
		this.ddayRe = this._initRe("disabledDays");
		this.sdateRe = this._initRe("specialDates");
		this.sdayRe = this._initRe("specialDays");
		this.minDate = this.doCustomDate($dp.minDate, $dp.minDate != $dp.defMinDate ? $dp.realFmt: $dp.realFullFmt, $dp.defMinDate);
		this.maxDate = this.doCustomDate($dp.maxDate, $dp.maxDate != $dp.defMaxDate ? $dp.realFmt: $dp.realFullFmt, $dp.defMaxDate);
		if (this.minDate.compareWith(this.maxDate) > 0) {
			$dp.errMsg = $lang.err_1;
		}
		if (this.loadDate()) {
			this._makeDateInRange();
			this.oldValue = $dp.el[$dp.elProp];
		} else {
			this.mark(false, 2);
		}
		sv("y");
		sv("M");
		sv("d");
		sv("H");
		sv("m");
		sv("s");
		$d.timeSpan.innerHTML = $lang.timeStr;
		$d.clearI.value = $lang.clearStr;
		$d.todayI.value = $lang.todayStr;
		$d.okI.value = $lang.okStr;
		this.initShowAndHide();
		this.initBtn();
		if ($dp.errMsg) {
			alert($dp.errMsg);
		}
		this.draw();
		hideSel();
		if ($dp.el.nodeType == 1) {
			$dp.attachEvent($dp.el, "onkeydown",
			function($) {
				if ($dp.el == ($.srcElement || $.target)) {
					k = ($.which == undefined) ? $.keyCode: $.which;
					if (k == 9) {
						if (!$dp.cal.checkAndUpdate()) {
							$.preventDefault ? $.preventDefault() : $.returnValue = false;
							$dp.cal.mark(false, 2);
							$dp.show();
						} else {
							$dp.cal.mark(true);
							$dp.hide();
						}
					}
				}
			});
		}
		function $() {
			var _,
			$;
			for (_ = 0; ($ = document.getElementsByTagName("link")[_]); _++) {
				if (attr($, "rel").indexOf("style") != -1 && attr($, "title")) {
					$.disabled = true;
					if (attr($, "title") == $dp.skin) {
						$.disabled = false;
					}
				}
			}
		}
	},
	_makeDateInRange: function() {
		var _ = this.checkRange();
		if (_ != 0) {
			var $;
			if (_ > 0) {
				$ = this.maxDate;
			} else {
				$ = this.minDate;
			}
			if ($dp.has.sd) {
				$dt.y = $.y;
				$dt.M = $.M;
				$dt.d = $.d;
			}
			if ($dp.has.st) {
				$dt.H = $.H;
				$dt.m = $.m;
				$dt.s = $.s;
			}
		}
	},
	splitDate: function(M, F, O, _, D, B, A, N, G) {
		var E;
		if (M && M.loadDate) {
			E = M;
		} else {
			E = new DPDate();
			if (M != "") {
				F = F || $dp.dateFmt;
				var K,
				P = 0,
				J,
				C = /yyyy|yyy|yy|y|MMMM|MMM|MM|M|dd|d|HH|H|mm|m|ss|s|DD|D|WW|W|w/g,
				Q = F.match(C);
				C.lastIndex = 0;
				if (G) {
					J = M.split(/\W+/);
				} else {
					var $ = 0,
					H = "^";
					while ((J = C.exec(F)) !== null) {
						if ($ > 0) {
							H += F.substring($, J.index);
						}
						$ = J.index - $;
						$ = C.lastIndex;
						switch (J[0]) {
						case "yyyy":
							H += "(\\d{4})";
							break;
						case "yyy":
							H += "(\\d{3})";
							break;
						default:
							if (new RegExp("MMMM|MMM|DD|D|WW|W|w").test(J[0])) {
								H += "(\\D+)";
							} else {
								H += "(\\d\\d?)";
							}
							break;
						}
					}
					H += ".*$";
					J = new RegExp(H).exec(M);
					P = 1;
				}
				if (J) {
					for (K = 0; K < Q.length; K++) {
						var L = J[K + P];
						if (L) {
							switch (Q[K]) {
							case "MMMM":
							case "MMM":
								E.M = I(Q[K], L);
								break;
							case "y":
							case "yy":
								L = pInt2(L, 0);
								if (L < 50) {
									L += 2000;
								} else {
									L += 1900;
								}
								E.y = L;
								break;
							case "yyy":
								E.y = pInt2(L, 0) + $dp.yearOffset;
								break;
							default:
								E[Q[K].slice( - 1)] = L;
								break;
							}
						}
					}
				} else {
					E.d = 32;
				}
			}
		}
		E.coverDate(O, _, D, B, A, N);
		return E;
		function I($, A) {
			var B = $ == "MMMM" ? $lang.aLongMonStr: $lang.aMonStr;
			for (var _ = 0; _ < 12; _++) {
				if (B[_].toLowerCase() == A.substr(0, B[_].length).toLowerCase()) {
					return _ + 1;
				}
			}
			return - 1;
		}
	},
	_initRe: function(B) {
		var A,
		_ = $dp[B],
		$ = "(?:";
		if (_) {
			for (A = 0; A < _.length; A++) {
				$ += this.doExp(_[A]);
				if (A != _.length - 1) {
					$ += "|";
				}
			}
			$ = new RegExp($ + ")");
		} else {
			$ = null;
		}
		return $;
	},
	update: function() {
		var $ = this.getNewDateStr();
		if ($dp.el[$dp.elProp] != $) {
			$dp.el[$dp.elProp] = $;
		}
		this.setRealValue();
	},
	setRealValue: function($) {
		var _ = $dp.$($dp.vel),
		$ = rtn($, this.getNewDateStr($dp.realFmt));
		if (_) {
			_.value = $;
		}
		attr($dp.el, "realValue", $);
	},
	doExp: function(s) {
		var ps = "yMdHms",
		arr,
		tmpEval,
		re = /#?\{(.*?)\}/;
		s = s + "";
		for (var i = 0; i < ps.length; i++) {
			s = s.replace("%" + ps.charAt(i), this.getP(ps.charAt(i), null, $tdt));
		}
		if (s.substring(0, 3) == "#F{") {
			s = s.substring(3, s.length - 1);
			if (s.indexOf("return ") < 0) {
				s = "return " + s;
			}
			s = $dp.win.eval('new Function("' + s + '");');
			s = s();
		} else {
			while ((arr = re.exec(s)) != null) {
				arr.lastIndex = arr.index + arr[1].length + arr[0].length - arr[1].length - 1;
				tmpEval = pInt(eval(arr[1]));
				if (tmpEval < 0) {
					tmpEval = "9700" + ( - tmpEval);
				}
				s = s.substring(0, arr.index) + tmpEval + s.substring(arr.lastIndex + 1);
			}
		}
		return s;
	},
	doCustomDate: function($, A, B) {
		var _;
		$ = this.doExp($);
		if (!$ || $ == "") {
			$ = B;
		}
		if (typeof $ == "object") {
			_ = $;
		} else {
			_ = this.splitDate($, A, null, null, 1, 0, 0, 0, true);
			_.y = ("" + _.y).replace(/^9700/, "-");
			_.M = ("" + _.M).replace(/^9700/, "-");
			_.d = ("" + _.d).replace(/^9700/, "-");
			_.H = ("" + _.H).replace(/^9700/, "-");
			_.m = ("" + _.m).replace(/^9700/, "-");
			_.s = ("" + _.s).replace(/^9700/, "-");
			if ($.indexOf("%ld") >= 0) {
				$ = $.replace(/%ld/g, "0");
				_.d = 0;
				_.M = pInt(_.M) + 1;
			}
			_.refresh();
		}
		return _;
	},
	loadDate: function() {
		var A,
		_;
		if ($dp.alwaysUseStartDate || ($dp.startDate != "" && $dp.el[$dp.elProp] == "")) {
			A = this.doExp($dp.startDate);
			_ = $dp.realFmt;
		} else {
			A = $dp.el[$dp.elProp];
			_ = this.dateFmt;
		}
		$dt.loadFromDate(this.splitDate(A, _));
		if (A != "") {
			var $ = 1;
			if ($dp.has.sd && !this.isDate($dt)) {
				$dt.y = $tdt.y;
				$dt.M = $tdt.M;
				$dt.d = $tdt.d;
				$ = 0;
			}
			if ($dp.has.st && !this.isTime($dt)) {
				$dt.H = $tdt.H;
				$dt.m = $tdt.m;
				$dt.s = $tdt.s;
				$ = 0;
			}
			return $ && this.checkValid($dt);
		}
		return 1;
	},
	isDate: function($) {
		if ($.y != null) {
			$ = doStr($.y, 4) + "-" + $.M + "-" + $.d;
		}
		return $.match(/^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/);
	},
	isTime: function($) {
		if ($.H != null) {
			$ = $.H + ":" + $.m + ":" + $.s;
		}
		return $.match(/^([0-9]|([0-1][0-9])|([2][0-3])):([0-9]|([0-5][0-9])):([0-9]|([0-5][0-9]))$/);
	},
	checkRange: function($, _) {
		_ = _ || $dt;
		var A = _.compareWith(this.minDate, $);
		if (A > 0) {
			A = _.compareWith(this.maxDate, $);
			if (A < 0) {
				A = 0;
			}
		}
		return A;
	},
	checkValid: function(A, $, _) {
		$ = $ || $dp.has.minUnit;
		var B = this.checkRange($, A);
		if (B == 0) {
			B = 1;
			if ($ == "d") {
				_ = _ || new Date(A.y, A.M - 1, A.d).getDay();
			}
			B = !this.testDisDay(_) && !this.testDisDate(A);
		} else {
			B = 0;
		}
		return B;
	},
	checkAndUpdate: function() {
		var A = $dp.el,
		$ = this,
		_ = $dp.el[$dp.elProp];
		if (_ != null) {
			if (_ != "" && !$dp.readOnly) {
				$.date.loadFromDate($.splitDate(_, $.dateFmt));
			}
			if (_ == "" || ($.isDate($.date) && $.isTime($.date) && $.checkValid($.date))) {
				if (_ != "") {
					$.newdate.loadFromDate($.date);
					$.update();
				} else {
					$.setRealValue("");
				}
			} else {
				return false;
			}
		}
		return true;
	},
	close: function() {
		hideSel();
		if (this.checkAndUpdate()) {
			this.mark(true);
			$dp.hide();
		} else {
			this.mark(false);
		}
	},
	_fd: function() {
		var _,
		F,
		$,
		J,
		C,
		G = new sb(),
		A = $lang.aWeekStr,
		B = $dp.firstDayOfWeek,
		H = "",
		E = "",
		K = new DPDate($dt.y, $dt.M, $dt.d, 0, 0, 0),
		I = K.y,
		D = K.M;
		C = 1 - new Date(I, D - 1, 1).getDay() + B;
		if (C > 1) {
			C -= 7;
		}
		G.a("<table class=WdayTable width=100% border=0 cellspacing=0 cellpadding=0>");
		G.a("<tr class=MTitle align=center>");
		if ($dp.isShowWeek) {
			G.a("<td>" + A[0] + "</td>");
		}
		for (_ = 0; _ < 7; _++) {
			G.a("<td>" + A[(B + _) % 7 + 1] + "</td>");
		}
		G.a("</tr>");
		for (_ = 1, F = C; _ < 7; _++) {
			G.a("<tr>");
			for ($ = 0; $ < 7; $++) {
				K.loadDate(I, D, F++);
				K.refresh();
				if (K.M == D) {
					J = true;
					if (K.compareWith($sdt, "d") == 0) {
						H = "Wselday";
					} else {
						if (K.compareWith($tdt, "d") == 0) {
							H = "Wtoday";
						} else {
							H = ($dp.highLineWeekDay && (0 == (B + $) % 7 || 6 == (B + $) % 7) ? "Wwday": "Wday");
						}
					}
					E = ($dp.highLineWeekDay && (0 == (B + $) % 7 || 6 == (B + $) % 7) ? "WwdayOn": "WdayOn");
				} else {
					if ($dp.isShowOthers) {
						J = true;
						H = "WotherDay";
						E = "WotherDayOn";
					} else {
						J = false;
					}
				}
				if ($dp.isShowWeek && $ == 0 && (_ < 4 || J)) {
					G.a("<td class=Wweek>" + getWeek(K, 1) + "</td>");
				}
				G.a("<td align=center ");
				if (J) {
					if (this.checkValid(K, "d", $)) {
						if (this.testSpeDay(new Date(K.y, K.M - 1, K.d).getDay()) || this.testSpeDate(K)) {
							H = "WspecialDay";
						}
						G.a('onclick="day_Click(' + K.y + "," + K.M + "," + K.d + ');" ');
						G.a("onmouseover=\"this.className='" + E + "'\" ");
						G.a("onmouseout=\"this.className='" + H + "'\" ");
					} else {
						H = "WinvalidDay";
					}
					G.a("class=" + H);
					G.a(">" + K.d + "</td>");
				} else {
					G.a("></td>");
				}
			}
			G.a("</tr>");
		}
		G.a("</table>");
		return G.j();
	},
	testDisDate: function(_) {
		var $ = this.testDate(_, this.ddateRe);
		return (this.ddateRe && $dp.opposite) ? !$: $;
	},
	testDisDay: function($) {
		return this.testDay($, this.ddayRe);
	},
	testSpeDate: function($) {
		return this.testDate($, this.sdateRe, 1);
	},
	testSpeDay: function($) {
		return this.testDay($, this.sdayRe, 1);
	},
	testDate: function($, _) {
		return _ ? _.test(this.getDateStr($dp.realFmt, $)) : 0;
	},
	testDay: function(_, $) {
		return $ ? $.test(_) : 0;
	},
	_f: function(p, c, r, e, isR) {
		var s = new sb(),
		fp = isR ? "r" + p: p;
		bak = $dt[p];
		s.a("<table cellspacing=0 cellpadding=3 border=0");
		for (var i = 0; i < r; i++) {
			s.a('<tr nowrap="nowrap">');
			for (var j = 0; j < c; j++) {
				s.a("<td nowrap ");
				$dt[p] = eval(e);
				if (this.checkValid($dt, p)) {
					s.a("class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown=\"");
					s.a("hide($d." + p + "D);$d." + fp + "I.value=" + $dt[p] + ";$d." + fp + 'I.blur();"');
				} else {
					s.a("class='invalidMenu'");
				}
				s.a(">" + (p == "M" ? $lang.aMonStr[$dt[p] - 1] : $dt[p]) + "</td>");
			}
			s.a("</tr>");
		}
		s.a("</table>");
		$dt[p] = bak;
		return s.j();
	},
	_fMyPos: function(_, A) {
		if (_) {
			var $ = _.offsetLeft;
			if ($IE) {
				$ = _.getBoundingClientRect().left;
			}
			A.style.left = $;
		}
	},
	_fM: function($) {
		this._fMyPos($, $d.MD);
		$d.MD.innerHTML = this._f("M", 2, 6, "i+j*6+1", $ == $d.rMI);
	},
	_fy: function(A, $) {
		var _ = new sb();
		$ = rtn($, $dt.y - 5);
		_.a(this._f("y", 2, 5, $ + "+i+j*5", A == $d.ryI));
		_.a("<table cellspacing=0 cellpadding=3 border=0 align=center><tr><td ");
		_.a(this.minDate.y < $ ? "class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown='if(event.preventDefault)event.preventDefault();event.cancelBubble=true;$c._fy(0," + ($ - 10) + ")'": "class='invalidMenu'");
		_.a(">\u2190</td><td class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown=\"hide($d.yD);$d.yI.blur();\">\xd7</td><td ");
		_.a(this.maxDate.y > $ + 10 ? "class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown='if(event.preventDefault)event.preventDefault();event.cancelBubble=true;$c._fy(0," + ($ + 10) + ")'": "class='invalidMenu'");
		_.a(">\u2192</td></tr></table>");
		this._fMyPos(A, $d.yD);
		$d.yD.innerHTML = _.j();
	},
	_fHMS: function($, A, _) {
		$d[$ + "D"].innerHTML = this._f($, 6, A, _);
	},
	_fH: function() {
		this._fHMS("H", 4, "i * 6 + j");
	},
	_fm: function() {
		this._fHMS("m", 2, "i * 30 + j * 5");
	},
	_fs: function() {
		this._fHMS("s", 1, "j * 10");
	},
	_fillQS: function($) {
		this.initQS();
		var C = this.QS,
		B = C.style,
		A = new sb();
		A.a('<table class=WdayTable width="' + $d.dDiv.offsetWidth + 'px" height="' + $d.dDiv.offsetHeight + 'px" border=0 cellspacing=0 cellpadding=0>');
		A.a('<tr class=MTitle><td><div style="float:left">' + $lang.quickStr + "</div>");
		if (!$) {
			A.a('<div style="float:right;cursor:pointer" onclick="hide($d.qsDivSel);">\xd7</div>');
		}
		A.a("</td></tr>");
		for (var _ = 0; _ < C.length; _++) {
			if (C[_]) {
				A.a("<tr><td nowrap='nowrap' class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onclick=\"");
				A.a("day_Click(" + C[_].y + ", " + C[_].M + ", " + C[_].d + "," + C[_].H + "," + C[_].m + "," + C[_].s + ');">');
				A.a("&nbsp;" + this.getDateStr(null, C[_]));
				A.a("</td></tr>");
			} else {
				A.a("<tr><td class='menu'>&nbsp;</td></tr>");
			}
		}
		A.a("</table>");
		$d.qsDivSel.innerHTML = A.j();
	},
	_dealFmt: function() {
		$(/w/);
		$(/WW|W/);
		$(/DD|D/);
		$(/yyyy|yyy|yy|y/);
		$(/MMMM|MMM|MM|M/);
		$(/dd|d/);
		$(/HH|H/);
		$(/mm|m/);
		$(/ss|s/);
		$dp.has.sd = ($dp.has.y || $dp.has.M || $dp.has.d) ? true: false;
		$dp.has.st = ($dp.has.H || $dp.has.m || $dp.has.s) ? true: false;
		$dp.realFullFmt = $dp.realFullFmt.replace(/%Date/, $dp.realDateFmt).replace(/%Time/, $dp.realTimeFmt);
		if ($dp.has.sd) {
			if ($dp.has.st) {
				$dp.realFmt = $dp.realFullFmt;
			} else {
				$dp.realFmt = $dp.realDateFmt;
			}
		} else {
			$dp.realFmt = $dp.realTimeFmt;
		}
		function $(_) {
			var $ = (_ + "").slice(1, 2);
			$dp.has[$] = _.exec($dp.dateFmt) ? ($dp.has.minUnit = $, true) : false;
		}
	},
	initShowAndHide: function() {
		var $ = 0;
		$dp.has.y ? ($ = 1, show($d.yI, $d.navLeftImg, $d.navRightImg)) : hide($d.yI, $d.navLeftImg, $d.navRightImg);
		$dp.has.M ? ($ = 1, show($d.MI, $d.leftImg, $d.rightImg)) : hide($d.MI, $d.leftImg, $d.rightImg);
		$ ? show($d.titleDiv) : hide($d.titleDiv);
		if ($dp.has.st) {
			show($d.tDiv);
			disHMS($d.HI, $dp.has.H);
			disHMS($d.mI, $dp.has.m);
			disHMS($d.sI, $dp.has.s);
		} else {
			hide($d.tDiv);
		}
		shorH($d.clearI, $dp.isShowClear);
		shorH($d.todayI, $dp.isShowToday);
		shorH($d.qsDiv, ($dp.has.d && $dp.qsEnabled));
		if ($dp.eCont) {
			hide($d.bDiv);
		}
	},
	mark: function(B, $) {
		var _ = $dp.el,
		D = $FF ? "class": "className";
		if (B) {
			C(_);
		} else {
			if ($ == null) {
				$ = $dp.errDealMode;
			}
			switch ($) {
			case 0:
				if (confirm($lang.errAlertMsg)) {
					_[$dp.elProp] = this.oldValue;
					C(_);
				} else {
					A(_);
				}
				break;
			case 1:
				_[$dp.elProp] = this.oldValue;
				C(_);
				break;
			case 2:
				A(_);
				break;
			}
		}
		function C($) {
			var A = $.className;
			if (A) {
				var _ = A.replace(/WdateFmtErr/g, "");
				if (A != _) {
					attr($, D, _);
				}
			}
		}
		function A($) {
			attr($, D, $.className + " WdateFmtErr");
		}
	},
	getP: function($, G, E) {
		E = E || $sdt;
		var H,
		F = [$ + $, $],
		_,
		C = E[$],
		A = function($) {
			return doStr(C, $.length);
		};
		switch ($) {
		case "w":
			C = getDay(E);
			break;
		case "D":
			var B = getDay(E) + 1;
			A = function($) {
				return $.length == 2 ? $lang.aLongWeekStr[B] : $lang.aWeekStr[B];
			};
			break;
		case "W":
			C = getWeek(E);
			break;
		case "y":
			F = ["yyyy", "yyy", "yy", "y"];
			G = G || F[0];
			A = function($) {
				return doStr(($.length < 4) ? ($.length < 3 ? E.y % 100: (E.y + 2000 - $dp.yearOffset) % 1000) : C, $.length);
			};
			break;
		case "M":
			F = ["MMMM", "MMM", "MM", "M"];
			A = function($) {
				return ($.length == 4) ? $lang.aLongMonStr[C - 1] : ($.length == 3) ? $lang.aMonStr[C - 1] : doStr(C, $.length);
			};
			break;
		}
		G = G || $ + $;
		if ("yMdHms".indexOf($) > -1 && $ != "y" && !$dp.has[$]) {
			if ("Hms".indexOf($) > -1) {
				C = 0;
			} else {
				C = 1;
			}
		}
		var D = [];
		for (H = 0; H < F.length; H++) {
			_ = F[H];
			if (G.indexOf(_) >= 0) {
				D[H] = A(_);
				G = G.replace(_, "{" + H + "}");
			}
		}
		for (H = 0; H < D.length; H++) {
			G = G.replace(new RegExp("\\{" + H + "\\}", "g"), D[H]);
		}
		return G;
	},
	getDateStr: function(C, A) {
		A = A || $sdt;
		C = C || this.dateFmt;
		var $ = "ydHmswW";
		for (var B = 0; B < $.length; B++) {
			var _ = $.charAt(B);
			C = this.getP(_, C, A);
		}
		if ($dp.has.D) {
			C = C.replace(/DD/g, "%dd").replace(/D/g, "%d");
			C = this.getP("M", C, A);
			C = C.replace(/\%dd/g, this.getP("D", "DD")).replace(/\%d/g, this.getP("D", "D"));
		} else {
			C = this.getP("M", C, A);
		}
		return C;
	},
	getNewP: function(_, $) {
		return this.getP(_, $, $dt);
	},
	getNewDateStr: function($) {
		return this.getDateStr($, $dt);
	},
	draw: function() {
		$d.rMD.innerHTML = "";
		if ($dp.doubleCalendar) {
			$c.autoPickDate = true;
			$d.className = "WdateDiv WdateDiv2";
			var $ = new sb();
			$.a("<table width=100% cellspacing=0 cellpadding=0 border=0><tr><td valign=top>");
			$.a(this._fd());
			$.a("</td><td valign=top>");
			$dt.attr("M", 1);
			$.a(this._fd());
			$d.rMI = $d.MI.cloneNode(true);
			$d.ryI = $d.yI.cloneNode(true);
			$d.rMD.appendChild($d.rMI);
			$d.rMD.appendChild($d.ryI);
			$d.rMI.value = $lang.aMonStr[$dt.M - 1];
			attr($d.rMI, "realValue", $dt.M);
			$d.ryI.value = $dt.y;
			_inputBindEvent("rM,ry");
			$d.rMI.className = $d.ryI.className = "yminput";
			$dt.attr("M", -1);
			$.a("</td></tr></table>");
			$d.dDiv.innerHTML = $.j();
		} else {
			$d.className = "WdateDiv";
			$d.dDiv.innerHTML = this._fd();
		}
		if (!$dp.has.d) {
			this._fillQS(true);
			showB($d.qsDivSel);
		} else {
			hide($d.qsDivSel);
		}
		this.autoSize();
	},
	autoSize: function() {
		var $ = parent.document.getElementsByTagName("iframe");
		for (var _ = 0; _ < $.length; _++) {
			if ($[_].contentWindow == window) {
				$[_].style.width = $d.offsetWidth + "px";
				$[_].style.height = $d.offsetHeight + "px";
			}
		}
	},
	pickDate: function() {
		while (!this.isDate($dt) && $dt.d > 0) {
			$dt.d--;
		}
		this.update();
		if (!$dp.eCont) {
			if (this.checkValid($dt)) {
				$c.mark(true);
				hide($dp.dd);
			} else {
				$c.mark(false);
			}
		}
		if ($dp.onpicked) {
			callFunc("onpicked");
		} else {
			if (this.oldValue != $dp.el[$dp.elProp] && $dp.el.onchange) {
				fireEvent($dp.el, "change");
			}
		}
	},
	initBtn: function() {
		$d.clearI.onclick = function() {
			if (!callFunc("onclearing")) {
				$dp.el[$dp.elProp] = "";
				$c.setRealValue("");
				hide($dp.dd);
				if ($dp.oncleared) {
					callFunc("oncleared");
				} else {
					if ($c.oldValue != $dp.el[$dp.elProp] && $dp.el.onchange) {
						fireEvent($dp.el, "change");
					}
				}
			}
		};
		$d.okI.onclick = function() {
			day_Click();
		};
		if (this.checkValid($tdt)) {
			$d.todayI.disabled = false;
			$d.todayI.onclick = function() {
				$dt.loadFromDate($tdt);
				day_Click();
			};
		} else {
			$d.todayI.disabled = true;
		}
	},
	initQS: function() {
		var H,
		B,
		C,
		A,
		F = [],
		E = 5,
		_ = $dp.quickSel.length,
		G = $dp.has.minUnit;
		if (_ > E) {
			_ = E;
		} else {
			if (G == "m" || G == "s") {
				F = [0, 15, 30, 45, 59, -60, -45, -30, -15, -1];
			} else {
				for (H = 0; H < E * 2; H++) {
					F[H] = $dt[G] - E + 1 + H;
				}
			}
		}
		for (H = B = 0; H < _; H++) {
			C = this.doCustomDate($dp.quickSel[H]);
			if (this.checkValid(C)) {
				this.QS[B++] = C;
			}
		}
		var D = "yMdHms",
		$ = [1, 1, 1, 0, 0, 0];
		for (H = 0; H <= D.indexOf(G); H++) {
			$[H] = $dt[D.charAt(H)];
		}
		for (H = 0; B < E; H++) {
			if (H < F.length) {
				C = new DPDate($[0], $[1], $[2], $[3], $[4], $[5]);
				C[G] = F[H];
				C.refresh();
				if (this.checkValid(C)) {
					this.QS[B++] = C;
				}
			} else {
				this.QS[B++] = null;
			}
		}
	}
};
function sb() {
	this.s = new Array();
	this.i = 0;
	this.a = function($) {
		this.s[this.i++] = $;
	};
	this.j = function() {
		return this.s.join("");
	};
}
function getWeek(A, B) {
	B = B || 0;
	var C = new Date(A.y, A.M - 1, A.d + B),
	_ = C.getDay();
	C.setDate(C.getDate() - (_ + 6) % 7 + 3);
	var $ = C.valueOf();
	C.setMonth(0);
	C.setDate(4);
	return Math.round(($ - C.valueOf()) / (7 * 86400000)) + 1;
}
function getDay($) {
	var _ = new Date($.y, $.M - 1, $.d);
	return _.getDay();
}
function show() {
	setDisp(arguments, "");
}
function showB() {
	setDisp(arguments, "block");
}
function hide() {
	setDisp(arguments, "none");
}
function setDisp(_, $) {
	for (i = 0; i < _.length; i++) {
		_[i].style.display = $;
	}
}
function shorH(_, $) {
	$ ? show(_) : hide(_);
}
function disHMS(_, $) {
	if ($) {
		_.disabled = false;
	} else {
		_.disabled = true;
		_.value = "00";
	}
}
function c(p, pv, notDraw) {
	if (p == "M") {
		pv = makeInRange(pv, 1, 12);
	} else {
		if (p == "H") {
			pv = makeInRange(pv, 0, 23);
		} else {
			if ("ms".indexOf(p) >= 0) {
				pv = makeInRange(pv, 0, 59);
			}
		}
	}
	if ($sdt[p] != pv && !callFunc(p + "changing")) {
		var func = 'sv("' + p + '",' + pv + ")",
		rv = $c.checkRange();
		if (rv == 0) {
			eval(func);
		} else {
			if (rv < 0) {
				_setAll($c.minDate);
			} else {
				if (rv > 0) {
					_setAll($c.maxDate);
				}
			}
		}
		if (!notDraw && "yMd".indexOf(p) >= 0) {
			$c.draw();
		}
		callFunc(p + "changed");
	}
	function _setAll($) {
		sv("y", $.y);
		sv("M", $.M);
		sv("d", $.d);
		if ($dp.has.st) {
			sv("H", $.H);
			sv("m", $.m);
			sv("s", $.s);
		}
	}
}
function day_Click(A, D, F, $, E, B) {
	var C = new DPDate($dt.y, $dt.M, $dt.d, $dt.H, $dt.m, $dt.s);
	$dt.loadDate(A, D, F, $, E, B);
	if (!callFunc("onpicking")) {
		var _ = C.y == A && C.M == D && C.d == F;
		if (!_ && arguments.length != 0) {
			c("y", A, true);
			c("M", D, true);
			c("d", F);
		}
		if ($c.autoPickDate || _ || arguments.length == 0) {
			$c.pickDate();
		}
	} else {
		$dt = C;
	}
}
function callFunc($) {
	var _;
	if ($dp[$]) {
		_ = $dp[$].call($dp.el, $dp);
	}
	return _;
}
function sv(_, $) {
	$ = $ || $dt[_];
	$sdt[_] = $dt[_] = $;
	if ("yHms".indexOf(_) >= 0) {
		$d[_ + "I"].value = $;
	}
	if (_ == "M") {
		attr($d.MI, "realValue", $);
		$d.MI.value = $lang.aMonStr[$ - 1];
	}
}
function attr($, A, _) {
	if (_ === undefined) {
		return $.getAttribute(A);
	} else {
		if ($.setAttribute) {
			$.setAttribute(A, _);
		}
	}
}
function makeInRange(A, _, $) {
	if (A < _) {
		A = _;
	} else {
		if (A > $) {
			A = $;
		}
	}
	return A;
}
function attachTabEvent($, _) {
	$.attachEvent("onkeydown",
	function() {
		var A = event,
		$ = (A.which == undefined) ? A.keyCode: A.which;
		if ($ == 9) {
			_();
		}
	});
}
function doStr($, _) {
	$ = $ + "";
	while ($.length < _) {
		$ = "0" + $;
	}
	return $;
}
function hideSel() {
	hide($d.yD, $d.MD, $d.HD, $d.mD, $d.sD);
}
function updownEvent($) {
	if ($c.currFocus == undefined) {
		$c.currFocus = $d.HI;
	}
	switch ($c.currFocus) {
	case $d.HI:
		c("H", $dt.H + $);
		break;
	case $d.mI:
		c("m", $dt.m + $);
		break;
	case $d.sI:
		c("s", $dt.s + $);
		break;
	}
}
function DPDate($, _, B, C, A, D) {
	this.loadDate($, _, B, C, A, D);
}
DPDate.prototype = {
	loadDate: function(_, C, E, $, D, A) {
		var B = new Date();
		this.y = pInt3(_, this.y, B.getFullYear());
		this.M = pInt3(C, this.M, B.getMonth() + 1);
		this.d = $dp.has.d ? pInt3(E, this.d, B.getDate()) : 1;
		this.H = pInt3($, this.H, B.getHours());
		this.m = pInt3(D, this.m, B.getMinutes());
		this.s = pInt3(A, this.s, B.getSeconds());
	},
	loadFromDate: function($) {
		if ($) {
			this.loadDate($.y, $.M, $.d, $.H, $.m, $.s);
		}
	},
	coverDate: function(_, C, E, $, D, A) {
		var B = new Date();
		this.y = pInt3(this.y, _, B.getFullYear());
		this.M = pInt3(this.M, C, B.getMonth() + 1);
		this.d = $dp.has.d ? pInt3(this.d, E, B.getDate()) : 1;
		this.H = pInt3(this.H, $, B.getHours());
		this.m = pInt3(this.m, D, B.getMinutes());
		this.s = pInt3(this.s, A, B.getSeconds());
	},
	compareWith: function(B, C) {
		var _ = "yMdHms",
		D,
		A;
		C = _.indexOf(C);
		C = C >= 0 ? C: 5;
		for (var $ = 0; $ <= C; $++) {
			A = _.charAt($);
			D = this[A] - B[A];
			if (D > 0) {
				return 1;
			} else {
				if (D < 0) {
					return - 1;
				}
			}
		}
		return 0;
	},
	refresh: function() {
		var $ = new Date(this.y, this.M - 1, this.d, this.H, this.m, this.s);
		this.y = $.getFullYear();
		this.M = $.getMonth() + 1;
		this.d = $.getDate();
		this.H = $.getHours();
		this.m = $.getMinutes();
		this.s = $.getSeconds();
		return ! isNaN(this.y);
	},
	attr: function(A, _) {
		if ("yMdHms".indexOf(A) >= 0) {
			var $ = this.d;
			this.d = 1;
			this[A] += _;
			this.refresh();
			this.d = $;
		}
	}
};
function pInt($) {
	return parseInt($, 10);
}
function pInt2($, _) {
	return rtn(pInt($), _);
}
function pInt3(_, $, A) {
	return pInt2(_, rtn($, A));
}
function rtn($, _) {
	return $ == null || isNaN($) ? _: $;
}
function fireEvent($, _) {
	if ($IE) {
		$.fireEvent("on" + _);
	} else {
		var A = document.createEvent("HTMLEvents");
		A.initEvent(_, true, true);
		$.dispatchEvent(A);
	}
}
function _foundInput(A) {
	var $,
	_,
	B = "y,M,H,m,s,ry,rM".split(",");
	for (_ = 0; _ < B.length; _++) {
		$ = B[_];
		if ($d[$ + "I"] == A) {
			return $.slice($.length - 1, $.length);
		}
	}
	return 0;
}
function _focus() {
	var $ = _foundInput(this);
	if ($ == "y") {
		this.className = "yminputfocus";
	} else {
		if ($ == "M") {
			this.className = "yminputfocus";
			this.value = attr(this, "realValue");
		} else {
			if ($) {
				$c.currFocus = this;
			} else {
				return;
			}
		}
	}
	this.select();
	$c["_f" + $](this);
	showB($d[$ + "D"]);
}
function _blur() {
	var p = _foundInput(this),
	isR,
	v = this.value,
	oldv = $dt[p];
	$dt[p] = pInt2(v, $dt[p]);
	if (p == "y") {
		isR = this == $d.ryI;
		if (isR && $dt.M == 12) {
			$dt.y -= 1;
		}
		this.className = "yminput";
	} else {
		if (p == "M") {
			isR = this == $d.rMI;
			if (isR) {
				if (oldv == 12) {
					$dt.y += 1;
				}
				$dt.attr("M", -1);
			}
			if ($sdt.M == $dt.M) {
				this.value = $lang.aMonStr[v - 1];
			}
			c("y", $dt.y, true);
			this.className = "yminput";
		}
	}
	eval('c("' + p + '",' + $dt[p] + ")");
	hide($d[p + "D"]);
}
function _inputKeydown() {
	var $ = event,
	_ = ($.which == undefined) ? $.keyCode: $.which;
	if (!$OPERA && !((_ >= 48 && _ <= 57) || (_ >= 96 && _ <= 105) || _ == 8 || _ == 46 || _ == 37 || _ == 39 || _ == 9)) {
		$.returnValue = false;
	}
}
function _inputBindEvent(A) {
	var $ = A.split(",");
	for (var _ = 0; _ < $.length; _++) {
		var B = $[_] + "I";
		$d[B].onfocus = _focus;
		$d[B].onblur = _blur;
		$d[B].attachEvent("onkeydown", _inputKeydown);
	}
}