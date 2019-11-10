package net.ntworld.intellijCodeCleaner

const val TOOL_WINDOW_NAME = "Code Cleaner"

const val PROJECT_INITIALIZED = "PROJECT_INITIALIZED"

const val TOGGLE_ANNOTATION = "TOGGLE_ANNOTATION"
const val TOGGLE_MAINTAINABILITY_FILTER = "TOGGLE_MAINTAINABILITY_FILTER"

const val REQUEST_ANALYZE = "REQUEST_ANALYZE"
const val REQUEST_ANALYZE_SUCCESS = "REQUEST_ANALYZE_SUCCESS"

const val REQUEST_STOP_ANALYZE = "REQUEST_STOP_ANALYZE"
const val REQUEST_STOP_ANALYZE_SUCCESS = "REQUEST_STOP_ANALYZE_SUCCESS"

const val CODE_STATISTIC_STARTED = "CODE_STATISTIC_STARTED"
const val CODE_STATISTIC_FINISHED = "CODE_STATISTIC_FINISHED"

const val CODE_ANALYZED = "CODE_ANALYZED"


const val ISSUE_NODE_VALUE_PATH = "path"
const val ISSUE_NODE_VALUE_LINE_BEGIN = "lineBegin"
const val ISSUE_NODE_VALUE_LINE_END = "lineEnd"

const val ISSUE_NODE_TEXT_LINE = "line"

const val ISSUE_NODE_TYPE_DIRECTORY = "Directory"
const val ISSUE_NODE_TYPE_FILE = "File"
const val ISSUE_NODE_TYPE_ISSUE = "Issue"
const val ISSUE_NODE_TYPE_RELATED_ISSUE = "RelatedIssue"
const val ISSUE_NODE_TYPE_ROOT = "Root"

const val TOOLTIP_ANALYZE_BUTTON = "Start running analyze process"
const val TOOLTIP_STOP_ANALYZE_BUTTON = "Stop current analyze process"
const val TOOLTIP_ANNOTATION_BUTTON = "Toggle annotation on editors"
const val TOOLTIP_FILTER_GOOD_ISSUE = """Toggle easy to fix issues"""
const val TOOLTIP_FILTER_MODERATE_ISSUE = "Toggle moderate issues"
const val TOOLTIP_FILTER_BAD_ISSUE = "Toggle worst issues"