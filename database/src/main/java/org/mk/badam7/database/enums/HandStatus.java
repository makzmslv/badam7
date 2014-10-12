package org.mk.badam7.database.enums;

public enum HandStatus
{
    IN_PROGRESS(1),
    COMPLETED(2),
    ABANDONED(3);

    private Integer status;

    HandStatus(Integer id)
    {
        this.status = id;
    }

    public Integer getStatusCode()
    {
        return status;
    }
}
