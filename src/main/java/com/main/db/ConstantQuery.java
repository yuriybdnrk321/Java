package com.main.db;

public class ConstantQuery {

    public static final String CREATE__TABLE_STUDENT = "CREATE TABLE `student` (\n" +
            "  `id_student` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `fullname` varchar(100) DEFAULT NULL,\n" +
            "  `sex` varchar(20) DEFAULT NULL,\n" +
            "  `bithdate` date DEFAULT NULL,\n" +
            "  `id_group` int(11) DEFAULT NULL,\n" +
            "  `number_records_book` varchar(50) DEFAULT NULL,\n" +
            "  `scholarchip` tinyint(4) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id_student`),\n" +
            "  UNIQUE KEY `id_student` (`id_student`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    public static final String CREATE_DB = "CREATE database University";

    public static final String CREATE_TEACHER = "CREATE TABLE `teacher` (\n" +
            "  `id_teacher` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `fullname` varchar(200) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `position` varchar(100) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `academic_degree` varchar(100) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `chair` varchar(100) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `subject` varchar(100) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `phoneNumber` varchar(20) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `home_address` varchar(200) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `id_faculty` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id_teacher`),\n" +
            "  UNIQUE KEY `id_teacher` (`id_teacher`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    public static final String CREATE_LESSON = "CREATE TABLE `lesson` (\n" +
            "  `id_lesson` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name_lesson` varchar(50) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `number_class` varchar(10) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `type_lesson` varchar(20) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `id_group` int(11) DEFAULT NULL,\n" +
            "  `id_teacher` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id_lesson`),\n" +
            "  UNIQUE KEY `id_lesson` (`id_lesson`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    public static final String CREATE_GROUP = "CREATE TABLE `group` (\n" +
            "  `id_group` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `number` varchar(10) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `direction` varchar(50) CHARACTER SET latin1 DEFAULT NULL,\n" +
            "  `id_faculty` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id_group`),\n" +
            "  UNIQUE KEY `id_group` (`id_group`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    public static final String CREATE_FACULTY = "CREATE TABLE `faculty` (\n" +
            "  `id_faculty` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name_faculty` varchar(200) DEFAULT NULL,\n" +
            "  `number_faculty` varchar(50) DEFAULT NULL,\n" +
            "  `count_student_in_faculty` int(11) DEFAULT NULL,\n" +
            "  `number_phone` varchar(20) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id_faculty`),\n" +
            "  UNIQUE KEY `id_faculty` (`id_faculty`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

}
