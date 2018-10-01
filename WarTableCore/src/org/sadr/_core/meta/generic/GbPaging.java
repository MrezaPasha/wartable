/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.meta.generic;

import java.util.Arrays;

/**
 * @author dev1
 */
public class GbPaging {

    //    public static final int PAGING_RANGE = 2;
    private int index;
    private int size;
    private Integer[] pages;
    private int pageCount;
    private int searchCount;
    private int totalCount;
    private int pageRage;

    private GbPaging() {
        size = 10;
        index = 1;
        pageRage = 2;
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  CUSTOM METHODS
    public static GbPaging init() {
        return new GbPaging();
    }

    public void calc() {
        if (size == -1) {
            pageCount = 1;
            index = 1;
            this.pages = new Integer[]{1};
        } else {

            pageCount = (int) Math.ceil(((double) searchCount / size));
            if (index > pageCount) {
                index = pageCount;
            }
            int i = index - pageRage,
                r;
            if (i < 1) {
                i = 1;
            }
            r = 2 * pageRage + i;
            if (r >= pageCount) {
                r = pageCount;
                if (r < index + pageRage) {
                    i -= (index + pageRage - r);
                    if (i < 1) {
                        i = 1;
                    }
                }
            }
            this.pages = new Integer[r - i + 1];
            for (int j = 0; i <= r; j++, i++) {
                pages[j] = i;
            }
        }
    }

    public int getFirstResult() {
        return (index - 1) * size;
    }

    public int getFrom() {
        return (index - 1) * size + 1;
    }

    public int getTo() {
        int s = index * size;
        return s > searchCount ? searchCount : s;
    }
    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=   METHODS

    public int getIndex() {
        return index;
    }

    public GbPaging setIndex(int index) {
        if (index > 0) {
            this.index = index;
        }
        return this;
    }

    public int getSize() {
        return size;
    }

    public GbPaging setSize(int size) {
//        if (size > 0) {
        this.size = size;
//        }
        return this;
    }

    public Integer[] getPages() {
        return pages;
    }

    public GbPaging setArrays(Integer[] arrays) {
        this.pages = arrays;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public GbPaging setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public GbPaging setSearchCount(int searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public GbPaging setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getPageRage() {
        return pageRage;
    }

    public GbPaging setPageRage(int pageRage) {
        this.pageRage = pageRage;
        return this;
    }

    @Override
    public String toString() {
        return "GbPaging{" + "index=" + index + ", size=" + size + ", arrays=" + Arrays.toString(pages) + ", pageCount=" + pageCount + ", searchCount=" + searchCount + ", totalCount=" + totalCount + '}';
    }

    public String toJson() {
        return "{" + "\"index\":" + index
            + ",\"size\":" + size
            + ",\"from\":" + getFrom()
            + ",\"to\":" + getTo()
            + ",\"start\":" + (index - 1)
            + ",\"end\":" + (index == pageCount ? 0 : index + 1)
            + ",\"pageCount\":" + pageCount
            + ",\"pages\":" + Arrays.toString(pages)
            + ",\"searchCount\":" + searchCount
            + ",\"totalCount\":" + totalCount + "}";

    }

}
