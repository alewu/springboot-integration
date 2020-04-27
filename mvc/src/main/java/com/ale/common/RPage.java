/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ale.common;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RPage<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private PageInfo pageInfo;

	@Getter
	@Setter
	private T data;

	public static <T> RPage<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> RPage<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}
	public static <T> RPage<T> ok(T data, PageInfo pageInfo) {
		return restResult(data, pageInfo,CommonConstants.SUCCESS, null);
	}

	public static <T> RPage<T> ok(T data, PageInfo pageInfo, String msg) {
		return restResult(data, pageInfo,CommonConstants.SUCCESS, msg);
	}
	public static <T> RPage<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> RPage<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> RPage<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> RPage<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> RPage<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}
	public Boolean isOK() {
		return this.code == 0;
	}
	private static <T> RPage<T> restResult(T data, PageInfo pageInfo, int code, String msg) {
		RPage<T> apiResult = new RPage<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setPageInfo(pageInfo);
		apiResult.setMsg(msg);
		return apiResult;
	}

	private static <T> RPage<T> restResult(T data, int code, String msg) {
		RPage<T> apiResult = new RPage<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}

