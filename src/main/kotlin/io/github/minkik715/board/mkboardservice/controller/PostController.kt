package io.github.minkik715.board.mkboardservice.controller

import io.github.minkik715.board.mkboardservice.aop.LoginCheck
import io.github.minkik715.board.mkboardservice.dto.CommonResponse
import io.github.minkik715.board.mkboardservice.dto.PostDTO
import io.github.minkik715.board.mkboardservice.service.PostService
import io.github.minkik715.board.mkboardservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.View
import java.util.*


@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
    private val userService: UserService,
    private val view: View
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun registerPost(userId: Long?, @RequestBody postDTO: PostDTO): ResponseEntity<CommonResponse<PostDTO>> {
        postService.register(userId!!, postDTO)
        val commonResponse = CommonResponse(HttpStatus.OK, "SUCCESS", "registerPost", postDTO)
        return ResponseEntity.ok(commonResponse)
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun myPostInfo(userId: Long?): ResponseEntity<CommonResponse<List<PostDTO>>> {
        val memberInfo = userService.getUserInfo(userId!!)
        val postDTOList: List<PostDTO> = postService.getMyProducts(memberInfo.id)
        val commonResponse = CommonResponse(HttpStatus.OK, "SUCCESS", "myPostInfo", postDTOList)
        return ResponseEntity.ok<CommonResponse<List<PostDTO>>>(commonResponse)
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePosts(
        userId: Long?,
        @PathVariable(name = "postId") postId: Long,
        @RequestBody postRequest: PostRequest
    ): ResponseEntity<CommonResponse<PostRequest>> {
        val memberInfo = userService.getUserInfo(userId!!)
        val postDTO: PostDTO = PostDTO(
            id = postId,
            userId = userId,
            name = postRequest.name,
            categoryId = postRequest.categoryId,
            isAdmin = false,
            contents = postRequest.contents,
            createTime = Date(),
            views = postRequest.views,
            fileId = postRequest.fileId,
            updateTime = postRequest.updateTime,
        )
        postService.updateProducts(postDTO)
        val commonResponse = CommonResponse(HttpStatus.OK, "SUCCESS", "updatePosts", postRequest)
        return ResponseEntity.ok(commonResponse)
    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deleteposts(
        userId: Long?,
        @PathVariable(name = "postId") postId: Long,
        @RequestBody postDeleteRequest: PostDeleteRequest
    ): ResponseEntity<CommonResponse<PostDeleteRequest>> {
        val memberInfo = userService.getUserInfo(userId!!)
        postService.deleteProduct(memberInfo.id, postId)
        val commonResponse = CommonResponse(HttpStatus.OK, "SUCCESS", "deleteposts", postDeleteRequest)
        return ResponseEntity.ok(commonResponse)
    }


    // -------------- response 객체 --------------
    data class PostResponse(
        val postDTO: List<PostDTO>
    )

    // -------------- request 객체 --------------
    data class PostRequest (
        val name: String,
        val contents: String,
        val views:Int ,
        val categoryId: Long ,
        val userId: Long ,
        val fileId: Long ,
        val updateTime: Date = Date()
    )

    data class PostDeleteRequest (
        val id: Long,
        val accountId: Long
    )

}