package com.dhxxn17.escape96app.ui.pages.filter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dhxxn17.escape96app.R
import com.dhxxn17.escape96app.databinding.FragmentFilterBinding
import com.dhxxn17.escape96app.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>(R.layout.fragment_filter) {

    private val levelArray = arrayListOf<Int>()
    private val args: FilterFragmentArgs by navArgs()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterBinding {
        return FragmentFilterBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        with(requireDataBinding()) {

            val locationParams = args.location.split(",")
            val levelParams = args.level.split(",")

            levelParams.forEach {
                when(it) {
                    "1" -> {
                        levelArray.add(1)
                        level1Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                    }
                    "2" -> {
                        levelArray.add(2)
                        level2Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                    }
                    "3" -> {
                        levelArray.add(3)
                        level3Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                    }
                    "4" -> {
                        levelArray.add(4)
                        level4Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                    }
                    "5" -> {
                        levelArray.add(5)
                        level5Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                    }
                    else -> {}
                }
            }

            locationParams.forEach {
                chipEditText.addNewChip(it)
            }


            closeBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            // 난이도
            level1Btn.setOnClickListener {
                if (levelArray.contains(1)) {
                    levelArray.remove(1)
                    level1Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                } else {
                    levelArray.add(1)
                    level1Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                }
            }

            level2Btn.setOnClickListener {
                if (levelArray.contains(2)) {
                    levelArray.remove(2)
                    level2Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                } else {
                    levelArray.add(2)
                    level2Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                }
            }

            level3Btn.setOnClickListener {
                if (levelArray.contains(3)) {
                    levelArray.remove(3)
                    level3Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                } else {
                    levelArray.add(3)
                    level3Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                }
            }

            level4Btn.setOnClickListener {
                if (levelArray.contains(4)) {
                    levelArray.remove(4)
                    level4Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                } else {
                    levelArray.add(4)
                    level4Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                }
            }

            level5Btn.setOnClickListener {
                if (levelArray.contains(5)) {
                    levelArray.remove(5)
                    level5Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                } else {
                    levelArray.add(5)
                    level5Btn.setBackgroundColor(Color.parseColor("#4d67C2A3"))
                }
            }


            // 초기화
            resetBtnLayout.setOnClickListener {
                // 난이도
                levelArray.clear()
                level1Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                level2Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                level3Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                level4Btn.setBackgroundColor(Color.parseColor("#ffffff"))
                level5Btn.setBackgroundColor(Color.parseColor("#ffffff"))


                // 위치
                chipEditText.removeAllViews()
            }


            // 적용하기
            applyBtn.setOnClickListener {
                val level = levelArray.joinToString(separator = ",")
                var location = ""
                for (i in 0 until chipEditText.chipGroup.childCount) {
                    val chip = chipEditText.chipGroup.getChildAt(i) as Chip
                    val chipText = chip.text.toString()
                    location += "$chipText,"
                }

                findNavController().previousBackStackEntry?.savedStateHandle?.set("level", level)
                findNavController().previousBackStackEntry?.savedStateHandle?.set("location", location)
                findNavController().popBackStack()
            }
        }
    }
}