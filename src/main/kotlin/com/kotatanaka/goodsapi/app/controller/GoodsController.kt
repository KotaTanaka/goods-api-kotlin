package com.kotatanaka.goodsapi.app.controller

import com.kotatanaka.goodsapi.domain.dto.request.CreateGoodsBody
import com.kotatanaka.goodsapi.domain.dto.response.CreateGoodsResponse
import com.kotatanaka.goodsapi.domain.exception.ValidationException
import com.kotatanaka.goodsapi.domain.service.GoodsService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 商品コントローラー
 *
 * @author kotatanaka
 */
@RestController
@RequestMapping("/app")
class GoodsController(private val goodsService: GoodsService) {

  /**
   * 商品登録
   *
   * @param body リクエストボディ
   * @param result BindingResult
   */
  @PostMapping("/goods")
  fun createGoods(
    @RequestBody @Validated body: CreateGoodsBody,
    result: BindingResult
  ): ResponseEntity<CreateGoodsResponse> {
    if (result.hasErrors()) throw ValidationException(result.fieldErrors)
    val response = goodsService.save(body)
    return ResponseEntity.ok(CreateGoodsResponse(response.id))
  }
}