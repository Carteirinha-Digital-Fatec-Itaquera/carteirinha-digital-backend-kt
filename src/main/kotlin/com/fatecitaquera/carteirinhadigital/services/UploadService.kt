package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.exceptions.InternalErrorException
import com.fatecitaquera.carteirinhadigital.exceptions.InvalidArgumentsException
import com.fatecitaquera.carteirinhadigital.exceptions.UnsupportedMediaTypeException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*
import java.util.Locale.getDefault

@Service
class UploadService(
    private val s3Client: S3Client,
    @Value("\${supabase.s3.bucket}") private val bucket: String,
    @Value("\${supabase.s3.endpoint-public}") private val endpoint: String

) {

    val supportedMediaTypes = listOf(".jpg", ".jpeg", ".png", ".webp")

    fun uploadImage(file: MultipartFile): String {
        val extension = getExtension(file)

        if (!supportedMediaTypes.contains(extension))
            throw UnsupportedMediaTypeException(RuntimeErrorEnum.ERR0015)

        val key = getName(extension)

        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.contentType)
            .build()

        s3Client.putObject(
            request,
            RequestBody.fromInputStream(file.inputStream, file.size)
        )
        return "$endpoint/$bucket/$key"
    }

    fun checkIfMultipartFileIsNull(file: MultipartFile?) {
        if (file == null)
            throw InvalidArgumentsException(RuntimeErrorEnum.ERR0018)
    }

    private fun getName(extension: String): String = UUID.randomUUID().toString() + extension

    private fun getExtension(objectFile: MultipartFile): String =
        if (objectFile.originalFilename != null && objectFile.originalFilename!!.contains("."))
            objectFile.originalFilename!!
                .substring(objectFile.originalFilename!!.lastIndexOf("."))
                .lowercase(getDefault())

        else throw UnsupportedMediaTypeException(RuntimeErrorEnum.ERR0017)
}